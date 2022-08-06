package com.adekunle.OrderService.command.api.saga;

import com.adekunle.CommonService.commons.ValidatePaymentCommand;
import com.adekunle.OrderService.command.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @StartSaga  // this starts the saga
    @SagaEventHandler(associationProperty = "orderId")  // this should be the id of the order
    private void handle(OrderCreatedEvent orderCreatedEvent){
        log.info("OrderCreatedEvent in saga for order id : {}",orderCreatedEvent.getOrderId());

        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder().build();
    }

}
