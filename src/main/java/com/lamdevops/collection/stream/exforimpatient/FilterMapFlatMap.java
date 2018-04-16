package com.lamdevops.collection.stream.exforimpatient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterMapFlatMap {

    @Test
    public void lowerCaseWords() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/exforimpatient/wordtext.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        lowercaseWords.forEach(System.out::println);
    }

    @Test
    public void firstLetter() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/exforimpatient/wordtext.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1));
        firstLetters.forEach(System.out::println);
    }

    @Test
    public void splitLetterToArray() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/exforimpatient/wordtext.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<Stream<String>> result = words.stream().map(w -> letter(w));

        result.forEach(ws -> {
            ws.forEach(wss -> System.out.print(wss + " "));
            System.out.println();
        });
    }

    @Test
    public void splitLetterToArrayFlatMap() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/exforimpatient/wordtext.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<String> result = words.stream().flatMap(w -> letter(w));

        result.forEach(ws -> System.out.print(ws + " "));
    }

    private Stream<String> letter(String w) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < w.length(); i++) {
            result.add(w.substring(i, i + 1));
        }
        return result.stream();
    }

    @Test
    public void limitRandom() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        randoms.forEach(System.out::println);
    }

    @Test
    public void skipOneElement() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("/Users/lamdevops/Documents/MyProjects/JavaCore/src/main/java/com/lamdevops/stream/exforimpatient/wordtext.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+")).filter(w -> !w.equals("the")).skip(1);
        words.forEach(System.out::println);
    }

    @Test
    public void combinedStream() {
        Stream<String> combined = Stream.concat(letter("Hello"), letter("World"));
        combined.forEach(System.out::println);
    }

    @Test
    public void distinctWords() {
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
        uniqueWords.forEach(System.out::println);
    }

    @Test
    public void compareLongestWord() {
        List<String> words = Arrays.asList("Distribute", "Completable", "Java", "Responsibility", "Nationality");
        Stream<String> longestOrderAsc= words.stream().sorted(Comparator.comparing(String::length));
        longestOrderAsc.forEach(System.out::println);
        System.out.println("");
        Stream<String> longestOrderDesc = words.stream().sorted(Comparator.comparing(String::length).reversed());
        longestOrderDesc.forEach(System.out::println);
    }

    @Test
    public void peekPower() {
        Stream<Double> powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20);
        powers.findFirst();
    }

    @Test
    public void peekWithRandom() {
        List<Double> powers = Stream.generate(Math::random)
                .peek(r -> System.out.println(r))
                .limit(20).collect(Collectors.toList());
        powers.size();
    }
}
