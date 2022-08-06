package com.adekunle.OrderService.command.api.data;

import com.adekunle.OrderService.command.api.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
