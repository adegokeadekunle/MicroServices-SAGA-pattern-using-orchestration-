package com.adekunle.UserService.projection;

import com.adekunle.CommonService.model.CardDetails;
import com.adekunle.CommonService.model.User;
import com.adekunle.CommonService.querries.GetUserPaymentQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserDetails(GetUserPaymentQuery userPaymentQuery) {
// ideally you get details of user from database.
        CardDetails cardDetails = CardDetails.builder()
                .name("Adekunle")
                .validMonth(11)
                .ValidYear(2024)
                .cvv(123)
                .cardNo("54627727388238383")
                .build();

        return  User.builder()
                .firstName("Adekunle")
                .LastName("Adegoke")
                .userId(userPaymentQuery.getUserId())
                .cardDetails(cardDetails)
                .build();

    }
}
