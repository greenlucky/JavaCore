package com.lamdevops.models;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Created by lamdevops on 7/26/17.
 */
public interface Optionals {

    static <R, T, Z> BiFunction<Optional<T>, Optional<R>, Optional<Z>> lift(BiFunction<? super T, ? super R, ? extends Z> function) {
        return (left, right) -> left.flatMap(leftVal -> right.map(rightVal -> function.apply(leftVal, rightVal)));
    }
}
