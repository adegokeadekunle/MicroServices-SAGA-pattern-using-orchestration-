package com.adekunle.productservice.command.api.serviceImpl;

import com.adekunle.productservice.command.api.commands.CreateProductCommand;
import com.adekunle.productservice.command.api.model.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl {

    private final CommandGateway commandGateway;  //from axon framework

    public String addProduct(ProductRestModel productRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();
        return commandGateway.sendAndWait(createProductCommand);
    }
}
