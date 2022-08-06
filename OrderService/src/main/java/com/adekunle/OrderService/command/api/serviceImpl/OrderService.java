package com.adekunle.OrderService.command.api.serviceImpl;

import com.adekunle.OrderService.command.api.model.OrderRestModel;

public interface OrderService {

    String createOrder(OrderRestModel order);
}
