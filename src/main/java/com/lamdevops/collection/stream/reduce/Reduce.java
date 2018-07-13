package com.lamdevops.collection.stream.reduce;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lamdevops on 8/11/17.
 */
public class Reduce {

    /**
     * Stream reduce() with Identify, Accumulator and Combiner.
     *
     * @throws Exception
     */
    @Test
    public void demo3() throws Exception {

        List<Integer> lstInts = Arrays.asList(2, 3, 4, 6);

        // Here result will be 2*2 + 2*3 + 2*4 + 2*6 = 30
        int result = lstInts.parallelStream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p + q);
        System.out.println(result);

        List<String> lstNames = Arrays.asList("Mohan", "Peter", "Fabien", "Phung");
        String result2 = lstNames.parallelStream().reduce("-", (s1, s2) -> s1 + s2, (p, q) -> p + q);
        System.out.println(result2);
    }
}
