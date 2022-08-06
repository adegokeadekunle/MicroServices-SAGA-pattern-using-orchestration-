package com.adekunle.OrderService.command.api.saga;

import com.adekunle.CommonService.commons.ValidatePaymentCommand;
import com.adekunle.CommonService.model.User;
import com.adekunle.CommonService.querries.GetUserPaymentQuery;
import com.adekunle.OrderService.command.api.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
@RequiredArgsConstructor
public class OrderProcessingSaga {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @StartSaga  // this starts the saga
    @SagaEventHandler(associationProperty = "orderId")  // this should be the id of the order
    private void handle(OrderCreatedEvent orderCreatedEvent){
        log.info("OrderCreatedEvent in saga for order id : {}",orderCreatedEvent.getOrderId());

        GetUserPaymentQuery getUserPaymentQuery = new GetUserPaymentQuery(orderCreatedEvent.getUserId());

        User user = null;

        try{
            user = queryGateway.query(getUserPaymentQuery, ResponseTypes.instanceOf(User.class)).join();
        }catch (Exception e) {
            log.error(e.getMessage());
        }

        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                .PaymentId(orderCreatedEvent.getProductId())
                .orderId(orderCreatedEvent.getOrderId())
                .cardDetails(user.getCardDetails())
                .build();
        
        commandGateway.sendAndWait(validatePaymentCommand);


    }

}
