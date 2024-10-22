package leo.landau.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User object")
public class OrderDto {

    private Long id;
    private Long userId;
    private Long itemId;
    private Long courierId;
    private int quantity;
    private BigDecimal amount;
    private OrderStatus status;

    public OrderDto() {
    }

    public OrderDto(Long id, Long userId, Long itemId, Long courierId, int quantity, BigDecimal amount, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.courierId = courierId;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    public static Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUserId(orderDto.getUserId());
        order.setItemId(orderDto.getItemId());
        order.setCourierId(orderDto.getCourierId());
        order.setQuantity(orderDto.getQuantity());
        order.setAmount(orderDto.getAmount());
        order.setStatus(orderDto.getStatus());
        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}


