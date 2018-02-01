package com.lamdevops.completionfuture.tut1_fundamentals;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FutureCombineMultiple {

    private CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Content page: " + pageLink;
        });
    }



    @Test
    public void combiningMultipleAllOf() throws ExecutionException, InterruptedException {
        List<String> webPageLinks = Arrays.asList(
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn",
                "http://vnexpress.net.vn"
        );

        List<CompletableFuture<String>> pageContentFutures = webPageLinks
                .stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));

        CompletableFuture<List<String>> allPageContentsFuture =
                allFutures.thenApply(v -> pageContentFutures.stream()
                        .map(pageContentFuture -> pageContentFuture.join())
                        .collect(Collectors.toList()));
        System.out.println(allPageContentsFuture.get());

         CompletableFuture<Long> countPage = allPageContentsFuture
                 .thenApply(page -> page
                         .stream()
                         .filter(content -> content.contains("http://vnexpress.net.vn")).count());

        System.out.println("Number of page: " + countPage.get());
    }

    @Test
    public void combiningMultipleAnyOf() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get());

    }
}
