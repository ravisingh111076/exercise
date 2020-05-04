package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.model.SecurityLimitOrder;
import com.acme.mytrader.repos.SecurityLookUpService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    TradingStrategyImpl tradingStrategy;
    ExecutionService executionService;
    SecurityLookUpService securityLookUpService;

    @Before
    public void setUp() {
       executionService = Mockito.mock(ExecutionService.class);
       securityLookUpService = Mockito.mock(SecurityLookUpService.class);
       tradingStrategy = new TradingStrategyImpl(executionService, securityLookUpService);
    }

    @Test
    public void testNothing() {
    }

    @Test
    public void testTrade_WhenStockPriceIsLessThanTippingPrice_TriggerBuy() {
        ArgumentCaptor<Double> priceArgument = ArgumentCaptor.forClass(Double.class);
        SecurityLimitOrder securityLimitOrder = new SecurityLimitOrder("security",100,56.0);
        when(securityLookUpService.lookUpLimitOrder(eq("security"))).thenReturn(securityLimitOrder);
        tradingStrategy.trade("security", 54);
        verify(executionService, times(1)).buy(eq("security"),priceArgument.capture(), eq(100));
        assertEquals(54, priceArgument.getValue().doubleValue(),0);
    }

    @Test
    public void testTrade_WhenStockPriceIsMoreThanTippingPrice_NoBuy() {
        SecurityLimitOrder securityLimitOrder = new SecurityLimitOrder("security",100,50.0);
        when(securityLookUpService.lookUpLimitOrder(eq("security"))).thenReturn(securityLimitOrder);
        tradingStrategy.trade("security", 54);
        verifyZeroInteractions(executionService);
    }
}
