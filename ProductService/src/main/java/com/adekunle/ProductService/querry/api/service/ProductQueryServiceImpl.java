package com.adekunle.productservice.querry.api.service;

import com.adekunle.productservice.command.api.model.ProductRestModel;
import com.adekunle.productservice.querry.api.querries.GetProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl {
    private final QueryGateway queryGateway;

    public List<ProductRestModel> getAllProducts(){
        GetProductQuery getProductQuery = new GetProductQuery();
        List<ProductRestModel> productRestModels =
        queryGateway.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)) //import ResponseTypes from axonFramework because it is a list, we select multipleInstancesOf
                .join(); // this is a completable future so we use the join method.
        return productRestModels;
    }
}
