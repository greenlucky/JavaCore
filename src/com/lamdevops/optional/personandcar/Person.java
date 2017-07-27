package com.lamdevops.optional.personandcar;

import java.util.Optional;

/**
 * Created by lamdevops on 7/27/17.
 */
public class Person {

    private Car car;

    private int minAge;


    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
}
