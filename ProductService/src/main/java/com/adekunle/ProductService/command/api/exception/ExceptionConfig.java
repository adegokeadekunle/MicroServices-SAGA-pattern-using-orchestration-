package com.adekunle.productservice.command.api.exception;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer){
        eventProcessingConfigurer.registerListenerInvocationErrorHandler("product",configuration -> new ProductEventsErrorHandler());
    }
}
