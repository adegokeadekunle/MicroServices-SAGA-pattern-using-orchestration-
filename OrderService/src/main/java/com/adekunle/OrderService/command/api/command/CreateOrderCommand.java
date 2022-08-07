package com.adekunle.OrderService.command.api.command;


import com.adekunle.CommonService.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private OrderStatus orderStatus;
}
