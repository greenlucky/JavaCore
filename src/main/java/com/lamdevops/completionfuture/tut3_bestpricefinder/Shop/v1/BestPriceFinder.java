package com.lamdevops.completionfuture.tut3_bestpricefinder.Shop.v1;

import com.lamdevops.completionfuture.tut3_bestpricefinder.models.ExchangeService;
import com.lamdevops.completionfuture.tut3_bestpricefinder.models.v1.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lamdevops on 8/5/17.
 */
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("BestBuy"),
            new Shop("PriceFine"),
            new Shop("Alibaba"),
            new Shop("Lazada"),
            new Shop("Amazon"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
                                + shop.getPrice(product), executor))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                    (price, rate) -> price * rate
                            );
            priceFutures.add(futurePriceInUSD);
        }

        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .map(price -> " price is " + price)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                }
            });

            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    double priceInEUR = shop.getPrice(product);
                    return priceInEUR * futureRate.get();
                }
            });

            priceFutures.add(futurePriceInUSD);
        }

        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(" price is " + priceFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return prices;
    }

    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for(Shop shop : shops) {
            CompletableFuture<String> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                    .thenCombine(
                            CompletableFuture.supplyAsync(
                                    () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                            (price, rate) -> price * rate
                            ).thenApply(price -> shop.getName()  + " price is " + price);
            priceFutures.add(futurePriceInUSD);

        }

        return priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD3(String product) {
        Stream<CompletableFuture<String>> pictureFuturesStream =
                shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(
                                CompletableFuture.supplyAsync(
                                        () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                        (price, rate) -> price * rate)
                                .thenApply(price -> shop.getName() + " price is " + price));

        List<CompletableFuture<String>> pricesFutures = pictureFuturesStream.collect(Collectors.toList());

        return pricesFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
