package com.adekunle.ShipmentService.command.api.aggregate;

import com.adekunle.CommonService.commands.ShipOrderCommand;
import com.adekunle.CommonService.enums.ShipmentStatus;
import com.adekunle.CommonService.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {

    @AggregateIdentifier
    private String ShipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {
    }
    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand shipOrderCommand) {
        //validate the command
        //publish the order shipped event
        OrderShippedEvent orderShippedEvent = OrderShippedEvent.builder()
                .orderId(shipOrderCommand.getOrderId())
                .shipmentId(shipOrderCommand.getShipmentId())
                .shipmentStatus(ShipmentStatus.SHIPPED)
                .build();
        AggregateLifecycle.apply(orderShippedEvent);
    }
    @EventSourcingHandler
    public void on (OrderShippedEvent event){
        this.orderId = event.getOrderId();
        this.ShipmentId = event.getShipmentId();
        this.shipmentStatus = event.getShipmentStatus();
    }

    }
