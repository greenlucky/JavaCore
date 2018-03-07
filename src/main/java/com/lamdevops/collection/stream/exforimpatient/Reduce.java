package com.lamdevops.collection.stream.exforimpatient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reduce {

    private List<String> words = null;

    @BeforeEach
    public void init() throws IOException {
        words = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\stream\\exforimpatient\\alice.txt")), StandardCharsets.UTF_8);
        words = Stream.of(content.split("\\PL+")).collect(Collectors.toList());
    }
    /**
     * Reduces section
     */
    @Test
    public void maxReduce() {
        Optional<String> largest = words.stream()
                .max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElseGet(String::new));
    }

    @Test
    public void startWithQFindFirst() {
        Optional<String> startWithQ = words.parallelStream().filter( s -> s.startsWith("Q")).findFirst();
        System.out.println("Word start with Q: " + startWithQ.orElse("There is no have the word start with Q"));
    }

    @Test
    public void startWithQFindAny() {
        Optional<String> startWithQ = words.parallelStream().filter( s -> s.startsWith("Q")).findAny();
        System.out.println("Word start with Q: " + startWithQ.orElse("There is no have the word start with Q"));
    }

    @Test
    public void matchAnyWordStartWithQ() {
        boolean aWordStartWithQ = words.parallelStream()
                .peek(s -> System.out.println("Checking: " + s))
                .anyMatch(s -> s.startsWith("Q"));
        System.out.println(aWordStartWithQ);
    }

    @Test
    public void matchAllWordStartWithQ() {
        boolean match = words.parallelStream().allMatch(s -> s.startsWith("Q"));
        System.out.println(match);
    }
}
