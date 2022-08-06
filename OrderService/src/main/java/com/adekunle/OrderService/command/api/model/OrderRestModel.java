package com.adekunle.OrderService.command.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRestModel {
    private String productId;
    private String userId;
    private String addressId;
    private String quantity;
}
