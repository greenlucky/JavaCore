package com.lamdevops.lambda.Functional._1Starting;

import java.util.function.Function;

public class Utils {

    public static Function<Integer, Integer> adder(Integer x) {
        return y -> x + y;
    }
}
