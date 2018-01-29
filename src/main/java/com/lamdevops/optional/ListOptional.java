package com.lamdevops.optional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lam.nm on 7/31/2017.
 */
public class ListOptional {

    @Test
    public void listAndCondition1() throws Exception {
        Optional<List<String>> lstStr = Optional.ofNullable(Arrays.asList());

        List<String> temp = lstStr.filter(ls -> ls.size() > 0).orElse(Arrays.asList("Empty"));
        System.out.println(temp);
    }
}
