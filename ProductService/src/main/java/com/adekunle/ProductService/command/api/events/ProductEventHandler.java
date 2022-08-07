package com.adekunle.productservice.command.api.events;

import com.adekunle.productservice.command.api.data.Product;
import com.adekunle.productservice.command.api.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product")
public class ProductEventHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
      //  throw new Exception("error occurred !");  when exception is thrown you can use the flow in exception handling
    }
    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }
}
