package com.acme.mytrader.price.impl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.strategy.TradingStrategy;

//Questions : Frequency to call
//Any fallback - what if stock price didn't return
//can use for multiple stocks ?
public class PriceListenerImpl implements PriceListener {

    PriceSource priceSource;
    TradingStrategy tradingStrategy;

    public PriceListenerImpl(PriceSource priceSource, TradingStrategy tradingStrategy) {
        this.priceSource = priceSource;
        this.priceSource.addPriceListener(this);
        this.tradingStrategy = tradingStrategy;
    }

    @Override
    public void priceUpdate(String security, double price) {
        tradingStrategy.trade(security, price);
    }
}
