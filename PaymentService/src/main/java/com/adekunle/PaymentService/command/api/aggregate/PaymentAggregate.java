package com.adekunle.PaymentService.command.api.aggregate;

import com.adekunle.CommonService.commands.ValidatePaymentCommand;
import com.adekunle.CommonService.events.PaymentProcessedEvent;
import com.adekunle.PaymentService.command.api.enums.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String PaymentId;
    private String orderId;
    private PaymentStatus paymentStatus;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        //validated the payment command CardDetails
        //publish the payment process event
        log.info("executing validatePaymentCommand for order id: {} and payment id: {} ",
                validatePaymentCommand.getOrderId(),
                validatePaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(
                validatePaymentCommand.getOrderId(),validatePaymentCommand.getPaymentId()
        );

        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("payment process event applied");

    }
    @EventSourcingHandler
    public void on(PaymentProcessedEvent event){
        this.orderId = event.getOrderId();
        this.PaymentId= event.getPaymentId();

    }
}
