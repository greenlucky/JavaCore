package com.lamdevops.collection.stream.exforimpatient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Sort {

    @Test
    public void sortWithLength() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("C:\\Users\\lam.nm\\Documents\\JavaCore\\src\\main\\java\\com\\lamdevops\\stream\\exforimpatient\\wordtext.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<String> longestWords = words.stream().sorted(Comparator.comparing(String::length).reversed());
        longestWords.forEach(System.out::println);
    }


}
