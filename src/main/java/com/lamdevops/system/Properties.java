package com.lamdevops.system;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class Properties {

    @Test
    public void getProperties() {

        Map<Object, Object> properties = System.getProperties();
        for(Map.Entry<Object, Object> item : properties.entrySet()) {
            System.out.println("[" + item.getKey() + "] ::: " + item.getValue());
        }
    }
}
