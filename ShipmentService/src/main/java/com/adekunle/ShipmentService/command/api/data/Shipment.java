package com.adekunle.ShipmentService.command.api.data;

import com.adekunle.CommonService.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ShipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;
}
