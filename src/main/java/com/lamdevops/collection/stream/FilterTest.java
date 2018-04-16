package com.lamdevops.collection.stream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;
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
    public void notNull() throws Exception {
        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");
        language.filter(Objects::nonNull).forEach(System.out::println);
    }

    public static void print(Stream<?> strs) {
        strs.forEach(System.out::println);
    }
}
