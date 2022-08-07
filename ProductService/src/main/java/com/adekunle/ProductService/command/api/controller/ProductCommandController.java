package com.adekunle.productservice.command.api.controller;

import com.adekunle.productservice.command.api.model.ProductRestModel;
import com.adekunle.productservice.command.api.serviceImpl.ProductCommandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandServiceImpl productservice;

    @PostMapping("/add_product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRestModel productRestModel){

        return ResponseEntity.ok().body(productservice.addProduct(productRestModel));
    }
}
