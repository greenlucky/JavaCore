package com.lamdevops.completionfuture.tutorial1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureException {

    @Test
    public void exceptionally() throws ExecutionException, InterruptedException {
        Integer age = 19;
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
           if(age < 0) {
               throw new IllegalArgumentException("Age can not be negative");
           } else if(age > 18) {
               return "Adult";
           } else {
               return "Child";
           }
        }).exceptionally(ex -> {
            System.out.println("Oops! we have an exception -" + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity: " + maturityFuture.get());
    }

    @Test
    public void handle() throws ExecutionException, InterruptedException {
        Integer age = 18;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            } else if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if(ex != null) {
                System.out.println("Oops! we have an exception -" + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity: " + maturityFuture.get());
    }

}
