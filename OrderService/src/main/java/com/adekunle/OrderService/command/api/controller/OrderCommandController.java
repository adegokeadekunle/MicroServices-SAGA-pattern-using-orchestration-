package com.adekunle.OrderService.command.api.controller;

import com.adekunle.OrderService.command.api.model.OrderRestModel;
import com.adekunle.OrderService.command.api.serviceImpl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderService orderService;

    @PostMapping("/create_order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRestModel orderRestModel){
        return ResponseEntity.ok().body(orderService.createOrder(orderRestModel));
    }
}
