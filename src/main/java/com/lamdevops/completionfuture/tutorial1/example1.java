package com.lamdevops.completionfuture.tutorial1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class example1 {

    /**
     * First test we'll create simple, which method .get() will block
     * the thread util .complete()
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
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
}
