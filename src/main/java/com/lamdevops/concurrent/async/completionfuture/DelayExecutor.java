package com.lamdevops.concurrent.async.completionfuture;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DelayExecutor {

    private static final long TIMEOUT3 = 3;
    private static final long TIMEOUT6 = 6;

    @Test
    public void simpleDelayExecutor() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeAsync(() -> {
            try {
                System.out.println("inside future: processing data...");
                return "JavaSampleApproach.com";
            } catch (Throwable e) {
                return "not detected";
            }
        }, CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS))
                .thenAccept(result -> System.out.println("accept: " + result));

        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running outside..." + i + " s");
        }
    }

    @Test
    public void orTimeOutIn3Seconds() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future =
                doWork("JavaSampleApproach")
                .orTimeout(TIMEOUT3, TimeUnit.SECONDS)
                .whenComplete((v, th) -> {
                    if(th == null) {
                        System.out.println("The result is: " + v);
                    } else {
                        System.out.println("Sorry, timeout in " + TIMEOUT3 + " seconds");
                    }
                });
        System.out.println("Result >> " + future.get());
    }

    @Test
    public void orTimeOutIn6Seconds() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future =
                doWork("JavaSampleApproach")
                        .orTimeout(TIMEOUT6, TimeUnit.SECONDS)
                        .whenComplete((v, th) -> {
                            if(th == null) {
                                System.out.println("The result is: " + v);
                            } else {
                                System.out.println("Sorry, timeout in " + TIMEOUT6 + " seconds");
                            }
                        });
        System.out.println("Result >> " + future.get());
    }

    @Test
    public void completeOnTimeout() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future =
                doWork("JavaSampleApproach")
                .completeOnTimeout("JavaTechnogoy", TIMEOUT3, TimeUnit.SECONDS)
                .whenComplete((v, th) -> {
                    if(th == null) {
                        System.out.println("The result is: " + v);
                    } else {
                        System.out.println("Sorry, timeout in " + TIMEOUT3 + " seconds.");
                    }
                });
        System.out.println("Result >>" + future.get());
    }
    private CompletableFuture<String> doWork(String s) {
        return CompletableFuture.supplyAsync(() -> {
            for(int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("running inside doWord()..." + i + " s");
            }
            return s + ".com";
        });
    }
}
