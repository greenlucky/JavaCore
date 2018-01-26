package com.lamdevops.completionfuture.tut3_bestpricefinder.Shop.v1;

import com.lamdevops.completionfuture.tut3_bestpricefinder.models.v1.Shop;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Created by lamdevops on 8/5/17.
 */
public class ShopAppTest {

    BestPriceFinder bestPriceFinder = new BestPriceFinder();

    @Test
    public void findBestPrice() throws Exception {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("future", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
        execute("combined USE CompletableFuture v1", () -> bestPriceFinder.findPricesInUSD("myPhone27S"));
        execute("combined USE CompletableFuture v2", () -> bestPriceFinder.findPricesInUSD2("myPhone27S"));
        execute("combined USE CompletableFuture v3", () -> bestPriceFinder.findPricesInUSD3("myPhone27S"));
    }

    @Test
    public void findPriceAsync() throws Exception {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        long invocationTime = ((System.nanoTime() - start)/1_000_000);

        Future<Double> futurePrice = shop.getPriceAsync("My favorite product");
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        long retrievalTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
