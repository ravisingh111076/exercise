package com.acme.mytrader;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.model.SecurityLimitOrder;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.impl.PriceListenerImpl;
import com.acme.mytrader.repos.SecurityLookUpService;
import com.acme.mytrader.scheduler.MonitorStockPriceTask;
import com.acme.mytrader.strategy.TradingStrategy;
import com.acme.mytrader.strategy.TradingStrategyImpl;

public class MainApp {
    public static void main(String [] args) {
        //spring or other dependency framework can be used.
        //Spring scheduler can be used
        //create a scheduler
        PriceSource priceSource = new PriceSource() {
            PriceListener listener;
            @Override
            public void addPriceListener(PriceListener listener) {
                this.listener = listener;
            }
            @Override
            public void removePriceListener(PriceListener listener) {}
            public void notifyPrice() {
                //connect and get price for stock.
                //this is hardcoded, looking for "IBM" only.
                // price should be feed with returned value.
                listener.priceUpdate("IBM",54);
            }
        };
        ExecutionService executionService = new ExecutionService() {
            @Override
            public void buy(String security, double price, int volume) {
                System.out.println("Buying security:" + security
                        +" ,price:"+ price +" ,volumn:"+ volume);
            }

            @Override
            public void sell(String security, double price, int volume) {}
        };
        SecurityLookUpService securityLookUpService = (security) -> new SecurityLimitOrder("IBM", 100, 55.0);
        TradingStrategy tradingStrategy = new TradingStrategyImpl(executionService, securityLookUpService);
        PriceListener priceListener = new PriceListenerImpl(priceSource, tradingStrategy);
        MonitorStockPriceTask monitorStockPriceTask = new MonitorStockPriceTask(priceSource);
        monitorStockPriceTask.execute();
        monitorStockPriceTask.shutdown();
    }
}
