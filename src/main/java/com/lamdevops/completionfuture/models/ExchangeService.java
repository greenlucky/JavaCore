package com.lamdevops.completionfuture.models;

import static com.lamdevops.completionfuture.models.Utils.delay;

/**
 * Created by lamdevops on 8/5/17.
 */
public class ExchangeService {

    public enum Money {
        USD(1.0),
        EUR(1.5387),
        GBP(1.69715),
        CAD(.92106),
        MXN(.07683);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }
}
