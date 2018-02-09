package com.lamdevops.concurrent.async.completionfuture.tut3_bestpricefinder.Shop;

import com.lamdevops.concurrent.async.completionfuture.tut3_bestpricefinder.models.AsynShop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

/**
 * Created by lamdevops on 8/5/17.
 */
public class AsynShopTest {

    @Test
    public void asynShopGetPrice() throws Exception {
        AsynShop shop = new AsynShop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPrice("myPhone");
        long inconationTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Invocation returned after " + inconationTime + " msecs");
        try {
            System.out.println("Price is " + futurePrice.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrivalTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Price returned after " + retrivalTime + " msecs");
    }
}
