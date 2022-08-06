package com.adekunle.OrderService.command.api.controller;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderCommandController {

    @PostMapping("/create_order")
    public ResponseEntity<String> createOrder(){
        return ResponseEntity.ok().body("Order created");
    }
}
