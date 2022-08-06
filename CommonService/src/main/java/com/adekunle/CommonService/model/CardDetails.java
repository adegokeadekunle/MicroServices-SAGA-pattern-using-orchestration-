package com.adekunle.CommonService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {

    private String name;
    private String cardNo;
    private Integer validMonth;
    private Integer ValidYear;
    private Integer cvv;

}
