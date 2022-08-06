package com.adekunle.OrderService.command.api.events;

import com.adekunle.OrderService.command.api.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private OrderStatus orderStatus;
}
