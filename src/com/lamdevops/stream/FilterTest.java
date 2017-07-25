package com.lamdevops.stream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lam.nm on 7/25/2017.
 */
public class FilterTest
{
    @Test
    public void filter() {
       String word = "This is example of stream spit";

        Stream<String> words = Stream.of(word.split(" "));
        print(words);
    }

    @Test
    public void generationString() throws Exception {
        Stream<String> echos = Stream.generate(()-> "Echo");
        print(echos);
    }

    @Test
    public void generationNumbers() throws Exception {

        Stream<Double> randoms = Stream.generate(Math::random);
        print(randoms);
    }

    @Test
    public void fileLine() throws Exception {
        File file = new File("C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\com\\lamdevops\\stream\\testfile.txt");
        Stream<String> lines = Files.lines(file.toPath()).filter(str->str.contains("line") && str.length() > 15).map(String::toLowerCase);
        print(lines);
    }

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

    @Test
    public void countElement() throws Exception {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> finalMap = new LinkedHashMap<>();

        result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue())
        .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
    }



    public void print(Stream<?> strs) {
        strs.forEach(w -> {
            System.out.println(w);
        });
    }
}
