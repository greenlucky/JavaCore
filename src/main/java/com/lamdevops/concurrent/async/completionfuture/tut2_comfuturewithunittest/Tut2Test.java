package com.lamdevops.concurrent.async.completionfuture.tut2_comfuturewithunittest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Tut2Test {

    @Test
    public void testSquareRoot() throws Exception {
        List<Integer> lstInput = Arrays.asList(10, 20, 30, 40);
        lstInput.stream()
                .map(data -> CompletableFuture.supplyAsync(() ->
                        getNumber(data))).map(com ->
                com.thenApply(n -> n*n)).map(t ->
                t.join()).forEach(s -> System.out.println(s));
    }

    private int getNumber(Integer v) {
        return v*v;
    }

    @Test
    public void testPrintList() {
        List<String> lstInput = Arrays.asList("A", "B", "C", "D");
        lstInput.stream()
                .map(data -> CompletableFuture.supplyAsync(() -> "Processing: " + data))
                .map(com -> com.thenAccept(s -> System.out.println(s)))
                .map(t -> t.join())
                .count();
    }
}
