package leo.landau.service;

import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Order;
import leo.landau.repository.OrderRepository;
import leo.landau.model.OrderStatus;

@Singleton
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private AccountService accountService;
    private NotificationService notificationService;

    public Order createOrder(Order order) {
        boolean paymentSuccessful = accountService.withdraw(order.getUserId(), order.getAmount());
        if (paymentSuccessful) {
            order.setStatus(OrderStatus.COMPLETED);
            notificationService.sendNotification(order.getUserId(), "Заказ успешно оформлен");
        } else {
            order.setStatus(OrderStatus.FAILED);
            notificationService.sendNotification(order.getUserId(), "Не удалось оформить заказ");
        }
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
