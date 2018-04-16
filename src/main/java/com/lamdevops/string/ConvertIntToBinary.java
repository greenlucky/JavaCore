package com.lamdevops.string;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given n 'n-bit' strings where n can be 10000, generate a new n-bit string which is different from all the others in the input.
 * For ex - if n is 2 and the input strings are 01 and 11, the output can be 00 or 10.
 * If n is 3 and the input strings are 110, 010 and 101, the output can be any one of 000, 001, 011, 100, 111.
 */
public class ConvertIntToBinary {

    public String intToBinary(int n, int numOfBits) {
        String binary = "";
        for (int i = 0; i < numOfBits; i++, n /= 2) {
            switch (n % 2) {
                case 0: binary = "0" + binary; break;
                case 1: binary = "1" + binary; break;
            }
        }
        return binary;
    }

    private void sequenceBinary(int numOfBi) {
        IntStream.range(1, numOfBi + 1).forEach(i -> {
            String seqBi = intToBinary(i, numOfBi);
            System.out.println(seqBi);
        });
    }


    @Test
    public void testIntToBinary2And3() throws Exception {
        String result = intToBinary(2, 3);
        assertEquals("010", result);
    }

    @Test
    public void testIntToBinary4And4() throws Exception {
        String result = intToBinary(4, 4);
        assertEquals("0100", result);
    }

    @Test
    public void testPrintSequenceBinaryVal2() {
       sequenceBinary(2);
    }

    @Test
    public void testPrintSequenceBinaryVal3() {
       sequenceBinary(3);
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString20() {
        sequenceBinary(8, 20, "0000101000, 1000001000");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString20One() {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture1 = sequenceBinary(1, (int) Math.pow(2, 20), 20, "0000101000, 1000001000");
        int size = 0;
        try {
            size  = completableFuture1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Executive time: " + (end - start));
        System.out.println(size);
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString22() {
        sequenceBinary(4, 24, "0000101000, 1000001000");
    }

    @Test
    public void testPrintSeqBinaryExcludeInputString22One() {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture1 = sequenceBinary(1, (int) Math.pow(2, 24), 20, "0000101000, 1000001000");
        int size = 0;
        try {
            size  = completableFuture1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Executive time: " + (end - start));
        System.out.println(size);
    }


    private void sequenceBinary(int div, int numOfStr, String exStr) {
        long start = System.currentTimeMillis();
        int num = (int) Math.pow(2, numOfStr);
        int parties = num/div;
        List<CompletableFuture<Integer>> completableFutures = new ArrayList<>();

        for(int i = 0; i < div ; i++) {
            CompletableFuture<Integer> completableFuture = sequenceBinary(1 + (parties * i), parties * (i + 1) , numOfStr, exStr);
            completableFutures.add(completableFuture);
        }

        int size =completableFutures.stream().mapToInt(i -> {
            try {
                return i.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
        long end = System.currentTimeMillis();
        System.out.println("Executive time: " + (end - start));
        System.out.println(size);
    }

    private CompletableFuture<Integer> sequenceBinary(int from, int to, int numOfStr, String exStr) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int i = from;
            int count = 0;
            while (i <= to) {
                String bina = intToBinary(i, numOfStr);
               /* if(i % 100000 == 0) {
                    System.out.println("Num of binary: " + i);
                }*/
                i++;
                count++;
            }
            return count;
        });

        return completableFuture;
    }
}
