package com.adekunle.PaymentService.command.api.events;

import com.adekunle.CommonService.events.PaymentProcessedEvent;
import com.adekunle.PaymentService.command.api.data.Payment;
import com.adekunle.PaymentService.command.api.data.PaymentRepository;
import com.adekunle.PaymentService.command.api.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {

    private final PaymentRepository paymentRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event){
        Payment payment = Payment.builder()
                .orderId(event.getOrderId())
                .paymentId(event.getPaymentId())
                .status(PaymentStatus.PAID)
                .timestamp(new Date())
                .build();
        paymentRepository.save(payment);
    }
}
