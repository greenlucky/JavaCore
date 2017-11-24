package com.lamdevops.completionfuture.models;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.lamdevops.completionfuture.models.Utils.delay;
import static com.lamdevops.completionfuture.models.Utils.format;

/**
 * Created by lamdevops on 8/4/17.
 */
public class AsynShop {

    private final String name;
    private final Random random;

    public AsynShop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }
}
