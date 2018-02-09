package com.lamdevops.concurrent.async.pharse;

import java.util.concurrent.Phaser;

public class LongRunningAction implements Runnable {

    private final Phaser ph;
    private final String threadName;

    public LongRunningAction(String threadName, Phaser ph) {
        this.threadName = threadName;
        this.ph = ph;
        ph.register();
    }

    @Override
    public void run() {
        System.out.println("This is phase " + ph.getPhase());
        System.out.println("Thread " + threadName + " before long running action");
        ph.arriveAndAwaitAdvance();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ph.arriveAndDeregister();

    }
}
