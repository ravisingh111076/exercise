package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.model.SecurityLimitOrder;
import com.acme.mytrader.repos.SecurityLookUpService;

public class TradingStrategyImpl implements TradingStrategy {
    ExecutionService executionService;
    //Want to decouple rule to trade
    SecurityLookUpService securityLookUpService;

    public TradingStrategyImpl(ExecutionService executionService, SecurityLookUpService securityLookUpService) {
        this.executionService = executionService;
        this.securityLookUpService = securityLookUpService;
    }

    @Override
    public void trade(String security, double price) {
        SecurityLimitOrder securityLimitOrder = securityLookUpService.lookUpLimitOrder(security);
        if(price < securityLimitOrder
                .getTippingPrice()) {
           executionService.buy(security, price, securityLimitOrder.getLotsToBuy());
        }
    }
}
