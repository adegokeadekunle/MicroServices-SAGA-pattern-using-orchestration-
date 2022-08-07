package com.adekunle.productservice.querry.api.projection;

import com.adekunle.productservice.command.api.data.Product;
import com.adekunle.productservice.command.api.data.ProductRepository;
import com.adekunle.productservice.command.api.model.ProductRestModel;
import com.adekunle.productservice.querry.api.querries.GetProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductProjection {
    private final ProductRepository repository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductQuery){
        List<Product> products = repository.findAll();
        List<ProductRestModel> productModels = products.stream().map(product -> ProductRestModel.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build())
                .collect(Collectors.toList());
        return productModels;
    }
}
