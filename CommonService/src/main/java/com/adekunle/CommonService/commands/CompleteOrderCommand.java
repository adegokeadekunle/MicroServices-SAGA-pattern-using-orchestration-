package com.adekunle.CommonService.commands;

import com.adekunle.CommonService.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOrderCommand {

    @TargetAggregateIdentifier
    private String OrderId;
    private OrderStatus orderStatus;
}
