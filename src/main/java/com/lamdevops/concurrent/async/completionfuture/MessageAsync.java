package com.lamdevops.concurrent.async.completionfuture;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageAsync {

    private ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }
    });

    @Test
    public void completedFutureExample() {
        String message = "message";
        CompletableFuture cf = CompletableFuture.completedFuture(message);
        assertTrue(cf.isDone());
        assertEquals(message, cf.getNow(null));
    }

    @Test
    public void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    @Test
    public void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                   assertTrue(Thread.currentThread().isDaemon());
                   randomSleep();
                   return s.toUpperCase();
                });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    @Test
    public void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
                    assertTrue(Thread.currentThread().isDaemon());
                    randomSleep();
                    return s.toUpperCase();
                }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    @Test
    public void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("theAccept message")
                .thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }

    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    @Test
    public void completeExceptionallyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.isDone();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) {
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }

        assertEquals("message upon cancel", exceptionHandler.join());
    }

    @Test
    public void cancelException() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> cf2 = cf.exceptionally(throwable -> "canceled message");
        assertTrue("Was not canceled", cf.cancel(true));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());
    }

    @Test
    public void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> deplayedLowerCase(s));
        CompletableFuture<String> cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> deplayedLowerCase(s)),
                s -> s + " from applyToEither");
        assertTrue(cf2.join().endsWith(" from applyToEither"));
    }

    /**
     * 11. Consuming Result of Either of Two Completed Stages
     */
    @Test
    public void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> deplayedLowerCase(s))
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> deplayedLowerCase(s)),
                        s -> result.append(s).append(" ").append("acceptEither"));
        cf.join();
        System.out.println(result.toString());
        assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
    }

    /**
     * 12. Running a Runnable upon Completion of Both Stages
     * This example shows how the dependent CompletableFuture
     * that executes a Runnable triggers upon completion of
     * both of two stages. Note all below stages run synchronously,
     * where a stage first converts a message string to uppercase,
     * then a second converts the same message string to lowercase.
     */
    @Test
    public void runAfterBothExample() {
        String orginal = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(orginal).thenApply(String::toUpperCase).runAfterBoth(
            CompletableFuture.completedFuture(orginal).thenApply(String::toLowerCase), () -> result.append("done"));
        System.out.println(result.toString());
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 13. Accepting Results of Both Stages in a Biconsumer
     * Instead of executing a Runnable upon completion of both stages,
     * using BiConsumer allows processing of their results if needed:
     */
    @Test
    public void thenAcceptBothExample() {
        String original = "Message";
        StringBuffer result = new StringBuffer();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenAcceptBoth(
                        CompletableFuture.completedFuture(original)
                        .thenApply(String::toLowerCase),
                        (s1, s2) -> result.append(s1 + s2));

        assertEquals("MESSAGEmessage", result.toString());
    }

    /**
     * 14. Applying a Bifunction on Results of Both Stages (i.e. Combining Their Results)
     * If the dependent CompletableFuture is intended to combine the results of two previous
     * CompletableFutures by applying a function on them and returning a result, we can use
     * the method thenCombine(). The entire pipeline is synchronous, so getNow() at the end
     * would retrieve the final result, which is the concatenation of the uppercase and the
     * lowercase outcomes.
     */
    @Test
    public void thenCombineExample() {
        String original = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
                .thenApply(s -> deplayedLowerCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> deplayedLowerCase(s)), (s1, s2) -> s1 + s2);
    }


    private String deplayedLowerCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }


    private void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleepEnough() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
