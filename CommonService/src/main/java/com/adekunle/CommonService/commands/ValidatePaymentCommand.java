package com.adekunle.CommonService.commands;

import com.adekunle.CommonService.model.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatePaymentCommand {

    @TargetAggregateIdentifier
    private String PaymentId;
    private String orderId;
    private CardDetails cardDetails;

}
