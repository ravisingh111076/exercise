package com.acme.mytrader.scheduler;
import com.acme.mytrader.price.PriceSource;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduler call price source in every 5 second.
 * Requires re-factory to scale for multiple security
 */
//it could be spring scheduler, externalize configurations.
//DELAY could be a cron expression.
//@Scheduled(cron = "${cronexpression}")
//public void execute() {
 //     priceSource.notifyPrice();
//}
//
public class MonitorStockPriceTask {
    ScheduledExecutorService scheduledExecutorService;

    PriceSource priceSource;
    //delay in seconds
    final Integer DELAY = 5;

    public MonitorStockPriceTask(PriceSource priceSource) {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        this.priceSource = priceSource;
    }

    public void execute() {
        scheduledExecutorService.schedule(() ->{ priceSource.notifyPrice();
        }, DELAY, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
