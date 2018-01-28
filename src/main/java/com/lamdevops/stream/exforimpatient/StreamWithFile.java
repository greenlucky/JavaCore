package com.lamdevops.stream.exforimpatient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamWithFile {

    public static final String FILE_PATH = "/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/files/alice.txt";

    @Test
    public void readFile() throws Exception {
        String contents = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        System.out.println(words.size());
        words.stream().forEach(w -> System.out.println(w));
    }

    @Test
    public void filterWordsLengthGreaterThan8WithoutStream() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long startTime = System.currentTimeMillis();
        long count = 0;
        for(String w: words) {
            if(w.length() > 8) count++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Number of words has length is greater than 8: " + count);
        System.out.println("Time processing: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void filterWordsLengthGreaterThan8() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long startTime = System.currentTimeMillis();
        long count = words.stream().filter(w -> w.length() > 8).count();
        long endTime = System.currentTimeMillis();
        System.out.println("Number of words has length is greater than 8: " + count);
        System.out.println("Time processing: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void filterWordsLengthGreaterThan8WithParallel() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long startTime = System.currentTimeMillis();
        long count = words.parallelStream().filter(w -> w.length() > 8).count();
        long endTime = System.currentTimeMillis();
        System.out.println("Number of words has length is greater than 8: " + count);
        System.out.println("Time processing: " + (endTime - startTime) + " milliseconds");
    }
}
