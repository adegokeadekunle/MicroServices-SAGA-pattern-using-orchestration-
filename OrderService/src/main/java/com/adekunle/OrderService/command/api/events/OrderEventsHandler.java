package com.adekunle.OrderService.command.api.events;

import com.adekunle.CommonService.enums.OrderStatus;
import com.adekunle.CommonService.events.OrderCompletedEvent;
import com.adekunle.OrderService.command.api.data.Order;
import com.adekunle.OrderService.command.api.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){

        Order order = new Order();
        BeanUtils.copyProperties(orderCreatedEvent, order);
        orderRepository.save(order);
    }
    @EventHandler
    public void on(OrderCompletedEvent event) throws Exception {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(()-> new Exception("Order not found"));
        order.setOrderStatus(event.getOrderStatus());
        orderRepository.save(order);

    }
}
