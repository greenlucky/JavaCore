package com.lamdevops.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by lamdevops on 7/27/17.
 */
public class OptionalInt {

    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if(value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0)
                    return i;
            }catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return 0;
    }

    public int readDurationOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalInt::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return Optional.empty();
        }
    }

    private Properties props;

   @BeforeEach
   public void init() throws Exception {
       props = new Properties();
       props.setProperty("a", "5");
       props.setProperty("b", "true");
       props.setProperty("c", "-3");
   }

    @Test
    public void testReadDuration() throws Exception {
        assertEquals(5, readDuration(props, "a"));
        assertEquals(0, readDuration(props, "b"));
        assertEquals(0, readDuration(props, "c"));
        assertEquals(0, readDuration(props, "d"));
    }

    @Test
    public void testReadDurationOptional() throws Exception {
        assertEquals(5, readDurationOptional(props, "a"));
        assertEquals(0, readDurationOptional(props, "b"));
        assertEquals(0, readDurationOptional(props, "c"));
        assertEquals(0, readDurationOptional(props, "d"));
    }

}
