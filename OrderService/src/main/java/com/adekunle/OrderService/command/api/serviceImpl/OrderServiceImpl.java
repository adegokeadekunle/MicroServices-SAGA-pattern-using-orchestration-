package com.adekunle.OrderService.command.api.serviceImpl;

import com.adekunle.OrderService.command.api.command.CreateOrderCommand;
import com.adekunle.OrderService.command.api.model.OrderRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService  {

    private final CommandGateway commandGateway;

    @Override
    public String createOrder(OrderRestModel order) {

        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(orderId)
                .addressId(order.getAddressId())
                .productId(order.getProductId())
                .userId(order.getUserId())
                .quantity(order.getQuantity())
                .orderStatus(OrderStatus.CREATED)
                .build();
        commandGateway.sendAndWait(createOrderCommand);
        return "Order created successfully";
    }
}
