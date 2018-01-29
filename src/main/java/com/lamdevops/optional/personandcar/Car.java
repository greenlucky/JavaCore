package com.lamdevops.optional.personandcar;

import java.util.Optional;

/**
 * Created by lamdevops on 7/27/17.
 */
public class Car {

    private Insurance insurance;

    public Optional<Insurance> getInsurance() {
        return Optional.ofNullable(insurance);
    }
}
