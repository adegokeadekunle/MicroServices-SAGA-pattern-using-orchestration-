package com.adekunle.CommonService.events;


import com.adekunle.CommonService.enums.ShipmentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShippedEvent {

    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;
}
