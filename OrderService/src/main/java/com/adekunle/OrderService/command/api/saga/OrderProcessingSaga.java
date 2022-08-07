package com.adekunle.OrderService.command.api.saga;

import com.adekunle.CommonService.commands.CompleteOrderCommand;
import com.adekunle.CommonService.commands.ShipOrderCommand;
import com.adekunle.CommonService.commands.ValidatePaymentCommand;
import com.adekunle.CommonService.enums.OrderStatus;
import com.adekunle.CommonService.events.OrderCompletedEvent;
import com.adekunle.CommonService.events.OrderShippedEvent;
import com.adekunle.CommonService.events.PaymentProcessedEvent;
import com.adekunle.CommonService.model.User;
import com.adekunle.CommonService.querries.GetUserPaymentQuery;
import com.adekunle.OrderService.command.api.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

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
                .orderId(orderCreatedEvent.getOrderId())
                .cardDetails(user.getCardDetails())
                .PaymentId(UUID.randomUUID().toString())
                .build();
        commandGateway.sendAndWait(validatePaymentCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event){
        log.info("PaymentProcessed event in Saga for order id: {}",event.getOrderId());

        try {
            ShipOrderCommand shipOrderCommand = ShipOrderCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.send(shipOrderCommand);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            // if error occurs , start a compensating transaction to roll back the events
        }
    }
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent event){
        log.info("Order Shipped Event in saga for Order id : {}",event.getOrderId());

        CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                .OrderId(event.getOrderId())
                .orderStatus(OrderStatus.APPROVED)
                .build();
        commandGateway.send(completeOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event){
        log.info("OrderCompletedEvent in Saga for Order id : {}",event.getOrderId());

    }
}
