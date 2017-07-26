package com.lamdevops.models;

import java.util.Optional;

/**
 * Created by lamdevops on 7/26/17.
 */
public interface NumberProvider {
    Optional<Integer> getNumber();
}
