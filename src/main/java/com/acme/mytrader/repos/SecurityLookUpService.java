package com.acme.mytrader.repos;

import com.acme.mytrader.model.SecurityLimitOrder;

public interface SecurityLookUpService {
    SecurityLimitOrder lookUpLimitOrder(String security);
}
