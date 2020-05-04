package com.acme.mytrader.strategy;

import java.util.function.DoubleSupplier;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public interface TradingStrategy {
    void trade(String security, double price);
}
