package com.lamdevops.lambda.Functional._1Starting;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Ex1 {

    @Test
    public void addAndConcatOne() {
        Function<Integer, Integer> addFunc = x -> x + 1;
        Function<String, String> concatFunc = x -> x + 1;

        Integer two = addFunc.apply(1);
        assertThat(two, is(2));

        String answer =  concatFunc.apply("0 + 1 = ");
        assertThat(answer, is("0 + 1 = 1"));
    }

    @Test
    public void addTwoElement() {
        Function<Integer, Function<Integer, Integer>> makeAdder = x -> y -> x + y;
        Function<Integer, Integer> add1 = makeAdder.apply(1);

        //or
        Function<Integer, Function<Integer, Integer>> add2 = Utils::adder;
    }

    @Test
    public void receiveFuncAsArgument() {
        Function<Integer, Integer> add = x -> x + 1;
        Function<Integer, Integer> mul = x -> x * 3;

        Integer res = mul.apply(add.apply(10));
        assertThat(res, is(33));
    }

    @Test
    public void binaryOperator() {
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        Integer res = sum.apply(1, 2);
        assertThat(res, is(3));
    }

    @Test
    public void composeReceiveFunc() {
        Function<Integer, Integer> add = x -> x + 1;
        Function<Integer, Integer> mul = x -> x * 3;
        BinaryOperator<Function<Integer, Integer>> compose = (f, g) -> x -> g.apply(f.apply(x));

        Function<Integer, Integer> h = compose.apply(add, mul);

        Integer res = h.apply(10);

        assertThat(res, is(33));

        //or
        Function<Integer, Integer> h2 = mul.compose(add);

        Integer res2 = h2.apply(10);

        assertThat(res2, is(33));
    }

    @Test
    public void sumPartially() {
        Function<Integer, Function<Integer, Integer>> sum = x -> y -> x + y;
        Function<Integer, Integer> plus10 = sum.apply(10);
        Integer res = plus10.apply(5);
        assertThat(res.intValue(), is(15));
    }


    @FunctionalInterface
    interface IntFx {
        int apply(int value);
    }

    @Test
    public void primitiveType() {
        IntFunction<IntFx> sum = x -> y -> x + y;
        IntFx sum10 = sum.apply(10);
        sum10.apply(4);
        assertThat(sum10, is(14));
    }
}
