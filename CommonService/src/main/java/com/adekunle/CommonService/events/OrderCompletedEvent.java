package com.adekunle.CommonService.events;

import com.adekunle.CommonService.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder()
public class OrderCompletedEvent {

    private String orderId;
    private OrderStatus orderStatus;

}
