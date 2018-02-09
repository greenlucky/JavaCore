package com.lamdevops.concurrent.async.pharse;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhaserExample {

    @Test
    public void testPhaserWithoutThread() {

        Phaser ph = new Phaser(1);
        assertEquals(0, ph.getPhase());
    }

    @Test
    public void testPhaserWithThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Phaser ph = new Phaser(1);
        executorService.submit(new LongRunningAction("thread-1", ph));
        executorService.submit(new LongRunningAction("thread-2", ph));
        executorService.submit(new LongRunningAction("thread-3", ph));
        ph.arriveAndAwaitAdvance();
        assertEquals(1, ph.getPhase());
        executorService.submit(new LongRunningAction("thread-4", ph));
        executorService.submit(new LongRunningAction("thread-5", ph));
        ph.arriveAndDeregister();
        ph.arriveAndAwaitAdvance();
        assertEquals(2, ph.getPhase());
        ph.arriveAndDeregister();
    }
}
