package com.lamdevops.concurrent.async.completionfuture;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFuture20Example {

    private ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }
    });

    /**
     * 1. Creating a Completed CompletableFuture
     * <p>
     * The simplest example creates an already completed CompletableFuture
     * with a predefined result. Usually this may act as the starting stage in your computation.
     */
    @Test
    public void completedFutureExample() {
        String message = "message";
        CompletableFuture cf = CompletableFuture.completedFuture(message);
        assertTrue(cf.isDone());
        assertEquals(message, cf.getNow(null));
    }

    /**
     * 2. Running a Simple Asynchronous Stage
     * <p>
     * The next example is how to create a stage that executes a Runnable asynchronously:
     * The takeaway of this example is two things:
     * A CompletableFuture is executed asynchronously when the method typically ends with the keyword  Async.
     * By default (when no Executor is specified), asynchronous execution uses the common ForkJoinPool
     * implementation, which uses daemon threads to execute the Runnable task. Note that this is
     * specific to CompletableFuture. Other CompletionStage implementations can override the default behavior.
     */
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

    /**
     * 3. Applying a Function on Previous Stage
     * <p>
     * The below example takes the completed CompletableFuture from example #1,
     * which bears the result string "message", and applies a function that converts it to uppercase:
     * Note the behavioral keywords in thenApply:
     * <p>
     * 1. then, which means that the action of this stage happens when the current stage completes
     * normally (without an exception). In this case, the current stage is already completed with
     * the value “message”.
     * <p>
     * 2. Apply, which means the returned stage will apply a Function on the result of the previous stage.
     * The execution of the Function will be blocking, which means that getNow() will only be reached when
     * the uppercase operation is done.
     */
    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApply(s -> {
                    assertFalse(Thread.currentThread().isDaemon());
                    return s.toUpperCase();
                });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    /**
     * 4. Asynchronously Applying a Function on Previous Stage
     * <p>
     * By appending the Async suffix to the method in the previous example,
     * the chained CompletableFuture would execute asynchronously (using ForkJoinPool.commonPool()).
     */
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

    /**
     * 5. Asynchronously Applying a Function on Previous Stage Using a Custom Executor
     * <p>
     * A very useful feature of asynchronous methods is the ability to provide an Executor
     * to use it to execute the desired CompletableFuture. This example shows how to use
     * a fixed thread pool to apply the uppercase conversion function:
     */
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

    /**
     * 6. Consuming Result of Previous Stage
     * <p>
     * If the next stage accepts the result of the current stage but does not need to
     * return a value in the computation (i.e. its return type is void), then instead
     * of applying a Function, it can accept a Consumer, hence the method thenAccept:
     * The Consumer will be executed synchronously, so we don’t need to join on the returned CompletableFuture.
     */
    @Test
    public void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("theAccept message")
                .thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 7. Asynchronously Consuming Result of Previous Stage
     * <p>
     * Again, using the async version of thenAccept, the chained CompletableFuture
     * would execute asynchronously:
     */
    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 8. Completing a Computation Exceptionally
     * <p>
     * Now let us see how an asynchronous operation can be explicitly completed
     * exceptionally, indicating a failure in the computation. For simplicity,
     * the operation takes a string and converts it to upper case, and we simulate
     * a delay in the operation of 1 second. To do that, we will use the
     * thenApplyAsync(Function, Executor) method, where the first argument is
     * the uppercase function, and the executor is a delayed executor that waits
     * for 1 second before actually submitting the operation to the common ForkJoinPool.
     * <p>
     * Let’s examine this example in detail:
     * <p>
     * - First, we create a CompletableFuture that is already completed with the value
     * "message". Next we call thenApplyAsync which returns a new CompletableFuture.
     * This method applies an uppercase conversion in an asynchronous fashion upon
     * completion of the first stage (which is already complete, thus the Function
     * will be immediately executed). This example also illustrates a way to delay
     * the asynchronous task using the delayedExecutor(timeout, timeUnit) method.
     * <p>
     * - We then create a separate “handler” stage, exceptionHandler, that handles any
     * exception by returning another message "message upon cancel".
     * <p>
     * - Next we explicitly complete the second stage with an exception. This makes the
     * join() method on the stage, which is doing the uppercase operation, throw a
     * CompletionException (normally join() would have waited for 1 second to get
     * the uppercase string). It will also trigger the handler stage.
     */
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

    /**
     * 9. Canceling a Computation
     * <p>
     * Very close to exceptional completion, we can cancel a computation via the
     * cancel(boolean mayInterruptIfRunning) method from the Future interface.
     * For CompletableFuture, the boolean parameter is not used because the
     * implementation does not employ interrupts to do the cancelation. Instead,
     * cancel() is equivalent to completeExceptionally(new CancellationException()).
     */
    @Test
    public void cancelException() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<String> cf2 = cf.exceptionally(throwable -> "canceled message");
        assertTrue("Was not canceled", cf.cancel(true));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());
    }

    /**
     * 10. Applying a Function to Result of Either of Two Completed Stages
     * <p>
     * The below example creates a CompletableFuture that applies a Function
     * to the result of either of two previous stages (no guarantees on which
     * one will be passed to the Function). The two stages in question are:
     * one that applies an uppercase conversion to the original string, and
     * another that applies a lowercase conversion:
     */
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
     * <p>
     * Similar to the previous example, but using a Consumer instead of a
     * Function (the dependent CompletableFuture has a type void):
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
     * <p>
     * If the dependent CompletableFuture is intended to combine the results of two previous
     * CompletableFutures by applying a function on them and returning a result, we can use
     * the method thenCombine(). The entire pipeline is synchronous, so getNow() at the end
     * would retrieve the final result, which is the concatenation of the uppercase and the
     * lowercase outcomes.
     */
    @Test
    public void thenCombineExample() {
        String original = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> deplayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> deplayedLowerCase(s)), (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }

    /**
     * 15. Asynchronously Applying a BiFunction on (i.e. Combining) Results of Both Stages
     * <p>
     * Similar to the previous example, but with a different behavior: since the two stages
     * upon which <em>CompletableFuture</em> depends both run asynchronously, the
     * <em>thenCombine()</em> method executes asynchronously, even though it lacks the
     * Async suffix. This is documented in the class Javadocs: <em>“Actions supplied
     * for dependent completions of non-async methods may be performed by the thread that
     * completes the current CompletableFuture, or by any other caller of a completion
     * method.”</em> Therefore, we need to join() on the combining CompletableFuture to
     * wait for the result.
     */
    @Test
    public void thenCombineAsyncExample() {
        String original = "message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> deplayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> deplayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * 16. Composing CompletableFutures
     * <p>
     * We can use composition using thenCompose() to accomplish the same computation
     * done in the previous two examples. This method waits for the first stage
     * (which applies an uppercase conversion) to complete. Its result is passed to
     * the specified Function which returns a CompletableFuture, whose result will
     * be the result of the returned CompletableFuture. In this case, the Function
     * takes the uppercase string (upper), and returns a CompletableFuture that
     * converts the original string to lowercase and then appends it to upper.
     */
    @Test
    public void thenComposeExample() {
        String original = "message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
                .thenApply(s -> deplayedUpperCase(s))
                .thenCompose(upper ->
                        CompletableFuture.completedFuture(original)
                                .thenApply(s -> deplayedLowerCase(s)).thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * Extends ex 16:
     * <p>
     * We try to lowerCase again upper value before append it with other value
     */
    @Test
    public void thenComposeExampleMod() {
        String original = "message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
                .thenApply(s -> deplayedUpperCase(s))
                .thenCompose(upper -> {
                    final String uppera = deplayedLowerCase(upper);
                    return CompletableFuture.completedFuture(original)
                            .thenApply(s -> deplayedLowerCase(s)).thenApply(s -> uppera + s);
                });
        assertEquals("messagemessage", cf.join());
    }

    @Test
    public void anyOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c", "d");

        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> deplayedUpperCase(s)))
                .collect(Collectors.toList());
        assertTrue(futures.size() == 4);
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if (th == null) {
                assertTrue(isUpperCase((String) res));
                System.out.println(res);
                result.append(res);
            }
        });

        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 18. Creating a Stage That Completes When All Stages Complete
     *
     * The next two examples illustrate how to create a CompletableFuture
     * that completes when all of several CompletableFutures completes,
     * in a synchronous and then asynchronous fashion, respectively.
     * The scenario is the same as the previous example: a list of
     * strings is provided where each element is converted to uppercase.
     */
    @Test
    public void allOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c", "d");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> deplayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> {
                        String res = cf.getNow(null);
                        assertTrue(isUpperCase(res));
                        System.out.println(res);
                    });
                    result.append("done");
                });

        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 19. Creating a Stage That Completes Asynchronously When All Stages Complete
     *
     * By switching to thenApplyAsync() in the individual CompletableFutures,
     * the stage returned by allOf() gets executed by one of the common pool
     * threads that completed the stages. So we need to call join() on it to wait
     * for its completion.
     */
    @Test
    public void allOfAsyncExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c", "d");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> deplayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                    result.append("done");
                });

        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    private boolean isUpperCase(String res) {
        for (int i = 0; i < res.length(); i++) {
            if (Character.isLowerCase(res.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private String deplayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    private String deplayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
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
