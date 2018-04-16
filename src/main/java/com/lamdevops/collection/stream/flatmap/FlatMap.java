package com.lamdevops.collection.stream.flatmap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lamdevops.collection.stream.FilterTest.print;

/**
 * Created by lam.nm on 7/26/2017.
 */
public class FlatMap {
    @Test
    public void flatMap() throws Exception {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        Stream<String> words = temp.flatMap(w -> Arrays.stream(w));

        print(words);
    }

    @Test
    public void flatMapPlus() throws Exception {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)) // Stream of List<Integer>
                .flatMap(List::stream)
                .map(integer -> integer + 1)
                .collect(Collectors.toList());
        print(together.stream());
    }
}
