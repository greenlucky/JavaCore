package com.lamdevops.completionfuture.bestpricefinder.Shop;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lamdevops on 8/5/17.
 */
public class ShopAppTest {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    @Test
    public void getBestPriceSynchronous() throws Exception {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
        bestPriceFinder.printPricesStream("myPhone27S");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) /1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
