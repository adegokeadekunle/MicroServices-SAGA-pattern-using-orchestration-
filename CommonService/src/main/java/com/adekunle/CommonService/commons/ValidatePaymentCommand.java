package com.adekunle.CommonService.commons;

import com.adekunle.CommonService.model.CardDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidatePaymentCommand {

    private String PaymentId;
    private String orderId;
    private CardDetails cardDetails;

}
