package com.lamdevops.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by lamdevops on 8/11/17.
 */
public class BlockingQueueExample {

    @Test
    public void testAdditional() throws Exception {
        BlockingQueue bq = new ArrayBlockingQueue(1000);

        Producer producer = new Producer(bq);
        Consumer consumer = new Consumer(bq);

        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(4000);

    }

    public static class Producer implements Runnable {
        private BlockingQueue bq = null;

        public Producer(BlockingQueue bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            Random rand = new Random();
            int res = 0;
            try {
                res = Addition(rand.nextInt(100), rand.nextInt(50));
                System.out.println("Producer: " + res);
                bq.put(res);
                Thread.sleep(1000);

                res = Addition(rand.nextInt(100), rand.nextInt(50));
                System.out.println("Producer: " + res);
                bq.offer(res, 500, TimeUnit.MILLISECONDS);
                Thread.sleep(1000);

                res = Addition(rand.nextInt(100), rand.nextInt(50));
                System.out.println("Producer: " + res);
                bq.put(res);
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void setBlockingQueue(BlockingQueue bq) {
            this.bq = bq;
        }

        public int Addition(int x, int y) {
            int result = 0;
            result = x + y;
            return result;
        }
    }

    public static class Consumer implements Runnable {

        protected BlockingQueue queue = null;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("Consumed: " + queue.take());
                System.out.println("Consumed: " + queue.take());
                System.out.println("Consumed: " + queue.poll(5, TimeUnit.SECONDS));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
