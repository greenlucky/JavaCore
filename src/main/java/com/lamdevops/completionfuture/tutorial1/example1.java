package com.lamdevops.completionfuture.tutorial1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class example1 {

    private ProductService productService;
    private UserService userService;

    @BeforeEach
    public void init() {
        productService = new ProductService();
        userService = new UserService();
    }

    /**
     * First test we'll create simple, which method .get() will block
     * the thread util .complete()
     * @throws Exception
     */
    @Test
    public void simpleCompletableFuture() throws Exception {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        completableFuture.complete("Future's result");
        String result = completableFuture.get();
        System.out.println(result);
    }

    /**
     * This test run completableFuture with async,
     * which is print the message.
     *
     * @throws Exception
     */
    @Test
    public void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a thread than the main thread.'");
        });

        future.get();
    }

    @Test
    public void runAsyncReturnValue() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });

        //Block and get the result of the Future
        String result = future.get();
        System.out.println(result);
    }

    @Test
    public void runAsyncReturnValueWithExecutor() throws Exception {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
           return "Result of the asynchronous computation";
        }, executor);

        String result = future.get();
        System.out.println(result);
    }

    @Test
    public void transformingAndActionV1() throws Exception {
        CompletableFuture<String> names = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Lam Nguyen";
        });

        //Attach a callback to the Future using theApply()
        CompletableFuture<String> greetingFuture = names.thenApply(name -> "Hello " + name);

        System.out.println(greetingFuture.get());
    }

    @Test
    public void transformingAndActionSequence() throws Exception {
        CompletableFuture<String> welComeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Lam Nguyen";
        }).thenApply(name -> "Hello " + name).thenApply(greeting -> greeting + ", Welcome to the CompletableFuture example!");

        System.out.println(welComeText.get());
    }

    /**
     * If you don’t want to return anything from your callback function and just
     * want to run some piece of code after the completion of the Future,
     * then you can use thenAccept() and thenRun() methods.
     * These methods are consumers and are often used as the
     * last callback in the callback chain. CompletableFuture.thenAccept()
     * takes a Consumer<T> and returns CompletableFuture<Void>.
     * It has access to the result of the CompletableFuture on which it is attached.
     */
    @Test
    public void supplyAsyncAndThenAccept() {
        CompletableFuture.supplyAsync(() -> productService.getProductDetail(1L)).thenAccept(product -> {
            System.out.println("Got the product detail from remote service " + product.getName());
        });
    }


    /**
     * While thenAccept() has access to the result of the CompletableFuture on which is attacked,
     * thenRun() doesn't even have access to the Future's result. It's takes a Runnable and resturns
     * CompletableFuture<void>.
     *
     */
    @Test
    public void supplyAsyncAndTheRun() {
        CompletableFuture.supplyAsync(() -> productService.getProductDetail(10000L)).thenRun(() -> {
            System.out.println("10000L not found");
        });
    }

    /**
     * *****************************************************************
     * CompletableFuture with thenApplyAsync
     * *****************************************************************
     */
    @Test
    public void supplyAsyncAndThenApplyAsync() throws Exception {
        CompletableFuture<String> res = CompletableFuture
                .supplyAsync(() -> "Lam Nguyen")
                .thenApplyAsync(result -> "Hi " + result);

        System.out.println(res.get());
    }

    @Test
    public void supplyAndThenApply() throws Exception {
        CompletableFuture<CompletableFuture<Double>> result = userService.getUserDetail(1)
                .thenApply(user -> userService.getCreditRating(user));
        System.out.println(result.get().get());
    }

    @Test
    public void supplyAndThenCompose() throws Exception {
        CompletableFuture<Double> result = userService.getUserDetail(1)
                .thenCompose(user -> userService.getCreditRating(user));
        System.out.println(result.get());
    }

    @Test
    public void supplyAndThenCombine() throws Exception {
        System.out.println("Retriveing weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 177.8;
        });

        System.out.println("Calculate BMI.");
        CompletableFuture<Double> combined = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        System.out.println("Your BMI is -" + combined.get());
    }

}
