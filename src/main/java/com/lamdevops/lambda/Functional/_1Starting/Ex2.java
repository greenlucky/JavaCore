package com.lamdevops.lambda.Functional._1Starting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Ex2 {

    @Test
    public void testMapping() throws Exception {
        Map<String, Integer> nameMap = new HashMap<>();
        Integer value1 = nameMap.computeIfAbsent("Join", s -> s.length());
        Integer value2 = nameMap.computeIfAbsent("Join", String::length);
    }

    @Test
    public void testComposeString() {
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteToString = quote.compose(intToString);

        assertEquals("'5'", quoteToString.apply(5));
    }

    @FunctionalInterface
    public interface ShortToByteFunction {
        byte applyAsByte(short s);
    }

    public byte[] transformArray(short[] array, ShortToByteFunction function) {
        byte[] transformedArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            transformedArray[i] = function.applyAsByte(array[i]);
        }

        return transformedArray;
    }

    @Test
    public void testShortToByteFunction() {
        short[] array = {(short) 1, (short) 2, (short) 3};
        byte[] transformedArray = transformArray(array, s -> (byte) (s * 2));

        byte[] expectedArray = {(byte) 2, (byte) 4, (byte) 6};
        assertArrayEquals(expectedArray, transformedArray);
    }

    @Test
    public void testTwoArityFunction() {
        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 40000);
        salaries.put("Freddy", 30000);
        salaries.put("Samuel", 50000);

        String key = "Freddy";
        salaries.replaceAll((name, oldValue) -> name.equals(key) ? oldValue + 10000 : oldValue);

        assertEquals((Integer)40000, salaries.get(key));
    }

    @Test
    public void testSupply() {
        Supplier<Double> lazyValue = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 9d;
        };
        
        Double valueSquared = squareLazy(lazyValue);
        assertEquals(Double.valueOf(81), valueSquared);
    }

    private Double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    @Test
    public void testFibonacci() {
        int[] fibs = {0, 1};
        Stream<Integer> fibonacci = Stream.generate(() -> {
           int result = fibs[1];
           int fib3 = fibs[0] + fibs[1];
           fibs[0] = fibs[1];
           fibs[1] = fib3;
           return result;
        });
        System.out.println(fibonacci.count());
    }

    @Test
    public void testReduce() {
        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);
        int sum = values.stream().reduce(0, (i1, i2) -> i1 + i2);
        assertEquals((int) 37, sum);
    }

}
