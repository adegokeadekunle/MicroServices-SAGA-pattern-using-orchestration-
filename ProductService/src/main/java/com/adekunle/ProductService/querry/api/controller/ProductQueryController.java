package com.adekunle.productservice.querry.api.controller;

import com.adekunle.productservice.command.api.model.ProductRestModel;
import com.adekunle.productservice.querry.api.service.ProductQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductQueryController {
   private final ProductQueryServiceImpl productQueryServiceImpl;

    @GetMapping("fetch_all")
    public ResponseEntity <List<ProductRestModel>> getAllProduct(){
        return ResponseEntity.ok().body(productQueryServiceImpl.getAllProducts());
    }
}
