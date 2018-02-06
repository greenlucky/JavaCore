package com.lamdevops.completionfuture;

import com.sun.tools.javac.code.Symbol;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChainAsyn {

    @Test
    public void chainAsyncWithBlockFunction() throws Exception {

        CompletableFuture.supplyAsync(this::findReceiver)
                .thenApply(this::sendMsg)
                .thenAccept(this::notify);

    }

    @Test
    public void chainAsyncWithAsyncFunction() throws Exception {

        CompletionStage<String> result = CompletableFuture
                .supplyAsync(this::findReceiver)
                .thenApply(this::sendMsgAsync).get();


    }

    @Test
    public void callbackAsyncFunction() throws Exception {
        CompletionStage<String> receiver = CompletableFuture.supplyAsync(this::findReceiver);

        receiver.thenApplyAsync(this::sendMsgAsync);
        receiver.thenApplyAsync(this::sendMsgAsync);

        receiver.thenApplyAsync(this::sendMsg);
        receiver.thenApplyAsync(this::sendMsg);
    }

    @Test
    public void exception() throws Exception {
        CompletableFuture.supplyAsync(this::failingMsg)
                .exceptionally(ex -> ex.toString())
                .thenAccept(this::notify);
    }

    @Test
    public void callDependingOnMulti() throws Exception {
        CompletableFuture<String> to = CompletableFuture.supplyAsync(this::findReceiver);
        CompletableFuture<String> text = CompletableFuture.supplyAsync(this::createContent);
        to.thenCombine(text, this::sendMsg);
    }

    @Test
    public void callDependingOnOne() throws Exception {
        CompletableFuture<String> to = CompletableFuture.supplyAsync(this::findReceiver);
        CompletableFuture<String> text = CompletableFuture.supplyAsync(this::createContent);
        to.acceptEither(text, this::sendMsg);
    }

    private <U extends String> U createContent() {
        return (U) "Hello, this is content of msg";
    }

    private void notify(Object o) {
        System.out.println((String) o);
    }


    private <U extends String> U failingMsg() {
      return (U) ((10/0) + "");
    }

    private <U extends String> CompletionStage<U> sendMsgAsync(String s) {

        CompletableFuture<U> withChain = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {

            IntStream.range(0, 1000).forEach(i -> {
                System.out.println(s + i);
            });

            withChain.complete((U) s);
        });

        return withChain;
    }

    private <U extends String> String findReceiver() {
        return "nguyenlamit86@gmail.com";
    }

    private void notify(String s) {
        System.out.println("I just send msg: " + s);
    }

    private <U extends String> U sendMsg(String email) {
        String title = "Send Message... to " + email;
        System.out.println(title);
        return (U) email;
    }

    private <U extends String> U sendMsg(String email, String text) {
        String title = "Send Message... to " + email;
        System.out.println(title);
        System.out.println(text);
        return (U) email;
    }
}
