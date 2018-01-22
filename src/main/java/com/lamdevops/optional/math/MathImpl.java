package com.lamdevops.optional.math;

import java.util.Optional;

import static com.lamdevops.optional.math.Optionals.lift;

/**
 * Created by lamdevops on 7/26/17.
 */
public class MathImpl {

    public Optional<Double> divideFirstTwo(NumberProvider numberProvider, Math math) {
        Optional<Integer> first = numberProvider.getNumber();
        Optional<Integer> seconds = numberProvider.getNumber();
        if (first.isPresent() && seconds.isPresent()) {
            double result = math.divide(first.get(), seconds.get());
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Double> divideFirstTowS(NumberProvider numberProvider, Math math) {
        return numberProvider.getNumber()
                .flatMap(first -> numberProvider.getNumber()
                        .map(second -> math.divide(first, second)));
    }

    public Optional<Double> divideFirstTowSS(NumberProvider numberProvider, Math math) {
        return lift(math::divide).apply(numberProvider.getNumber(), numberProvider.getNumber());
    }
}
