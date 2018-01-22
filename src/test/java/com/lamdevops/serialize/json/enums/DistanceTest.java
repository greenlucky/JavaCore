package com.lamdevops.serialize.json.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceTest {
    @Test
    public void getUnit() throws Exception {
        Object result = new ObjectMapper().writeValueAsString(Distance.MILE);
        System.out.println(result);
    }

    @Test
    public void getMeters() throws Exception {
    }

}