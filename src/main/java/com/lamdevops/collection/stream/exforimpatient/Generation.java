package com.lamdevops.collection.stream.exforimpatient;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Generation {

    @Test
    public void generateString() {
        Stream<String> echos = Stream.generate(() -> "Echo");
        echos.forEach(System.out::println);
    }

    @Test
    public void generateRandom() {
        Stream<Double> random = Stream.generate(Math::random);
        random.forEach(System.out::println);
    }

    @Test
    public void generateInteger() {
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        integers.forEach(System.out::println);
    }
}
