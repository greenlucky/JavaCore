package com.lamdevops.optional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by lamdevops on 7/27/17.
 */
public class StreamAndOptional {

    @Test
    public void testSteamAndOptional() throws Exception {
        List<Optional<String>> listOfOptionals = Arrays.asList(
                Optional.empty(),
                Optional.of("foo"),
                Optional.empty(),
                Optional.of("bar")
        );

        //usering filter
        List<String> filterList = listOfOptionals
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        assertEquals(2, filterList.size());

        //flatmap
        List<String> flatMapList = listOfOptionals
                .stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList());
        assertEquals(2, flatMapList.size());

        //flatmap
        List<String> flatMapList2 = listOfOptionals
                .stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
        assertEquals(2, flatMapList2.size());
    }

}
