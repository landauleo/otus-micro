package leo.landau.service;

import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Order;
import leo.landau.model.OrderStatus;
import leo.landau.repository.OrderRepository;

@Singleton
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountService accountService;
    private final NotificationService notificationService;
    private final InventoryService inventoryService;
    private final DeliveryService deliveryService;

    public OrderService(OrderRepository orderRepository, AccountServiceImpl accountService,
                        NotificationService notificationService, InventoryService inventoryService,
                        DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
        this.notificationService = notificationService;
        this.inventoryService = inventoryService;
        this.deliveryService = deliveryService;
    }

    public Order createOrder(Order order) {
        boolean isSuccess = executeOrderSaga(order);
        if (isSuccess) {
            // Сохранение заказа в базу данных
            order.setStatus(OrderStatus.COMPLETED);
            notificationService.sendNotification(order.getUserId(), "Заказ успешно оформлен");
            return order;
        } else {
            order.setStatus(OrderStatus.FAILED);
            notificationService.sendNotification(order.getUserId(), "Не удалось оформить заказ");
            return null;
        }
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private boolean executeOrderSaga(Order order) {
        boolean paymentSuccess = false;
        boolean inventorySuccess = false;
        boolean deliverySuccess = false;

        try {
            // Шаг 1: Обработка платежа
            paymentSuccess = accountService.withdraw(order.getUserId(), order.getAmount());
            if (!paymentSuccess) {
                throw new RuntimeException("Insufficient funds");
            }

            // Шаг 2: Резервирование товара на складе
            inventorySuccess = inventoryService.reserveItem(order.getItemId(), order.getQuantity());
            if (!inventorySuccess) {
                throw new RuntimeException("Inventory reservation failed");
            }

            // Шаг 3: Резервирование курьера
            deliverySuccess = deliveryService.reserveCourier(order.getCourierId());
            if (!deliverySuccess) {
                throw new RuntimeException("Courier reservation failed");
            }

            return true;
        } catch (Exception e) {
            // Откат изменений в случае ошибки
            if (paymentSuccess) {
                accountService.refund(order.getUserId(), order.getAmount());
            }
            if (inventorySuccess) {
                inventoryService.cancelReservation(order.getItemId(), order.getQuantity());
            }
            if (deliverySuccess) {
                deliveryService.cancelCourierReservation(order.getId());
            }
            return false;
        }
    }

}
