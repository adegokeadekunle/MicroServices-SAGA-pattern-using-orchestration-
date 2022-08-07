package com.adekunle.ShipmentService.command.api.event;

import com.adekunle.CommonService.events.OrderShippedEvent;
import com.adekunle.ShipmentService.command.api.data.Shipment;
import com.adekunle.ShipmentService.command.api.data.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipmentEventHandler {

    private final ShipmentRepository shipmentRepository;
    @EventHandler
    public void on(OrderShippedEvent event){
        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(event, shipment);
        shipmentRepository.save(shipment);
    }

}
