package com.acme.mytrader.model;

public class SecurityLimitOrder {
    private String name;
    private int limitOrder;
    private double tippingPrice;
    public SecurityLimitOrder(String name, int limitOrder, double tippingPrice) {
        this.name = name;
        this.limitOrder = limitOrder;
        this.tippingPrice = tippingPrice;
    }
    public String getName() { return name; }
    public int getLotsToBuy() { return limitOrder; }
    public double getTippingPrice() { return tippingPrice; }
}

