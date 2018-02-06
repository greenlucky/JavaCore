package com.lamdevops.lambda;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * Created by lamdevops on 8/11/17.
 */
public class TomicExample {

    @Test
    public void testTomicSumList() throws Exception {
        LongAccumulator acc = new LongAccumulator(Long::max, 5);
        acc.accumulate(10);

        long result = acc.get();
        System.out.println(result);

    }
}
