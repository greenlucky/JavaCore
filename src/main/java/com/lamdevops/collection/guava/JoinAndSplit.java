package com.lamdevops.collection.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinAndSplit {

    /**
     * 1. Convert List into String Using Joiner
     */
    @Test
    public void whenConvertListToStringThenConverted() {
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Tom");
        String  result = Joiner.on(",").join(names);

        assertEquals(result, "John,Jane,Adam,Tom");
    }

    /**
     * 2. Convert Map to String Using Joiner
     */
    @Test
    public void whenConvertMapToStringThenConverted() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ")
                .withKeyValueSeparator(" = ")
                .join(salary);
        assertThat(result, containsString("John = 1000"));
        assertThat(result, containsString("Jane = 1500"));
    }

    @Test
    public void whenJoinNestedCollectionsThenJoined() {
        List<ArrayList<String>> nested = Lists.newArrayList(
                Lists.newArrayList("apple", "banana", "orange"),
                Lists.newArrayList("cat", "dog", "bird"),
                Lists.newArrayList("John", "Jane", "Adam"));

        String result = Joiner.on(";").join(Iterables.transform(nested,
                new Function<ArrayList<String>, String>() {
                    @Nullable
                    @Override
                    public String apply(@Nullable ArrayList<String> input) {
                        return Joiner.on("-").join(input);
                    }
                }
        ));

        assertThat(result, containsString("apple-banana-orange"));
        assertThat(result, containsString("cat-dog-bird"));
        assertThat(result, containsString("apple-banana-orange"));
    }

    /**
     * 5. Handle null values while using Joiner
     *
     * To skip null values while joining collection use skipNulls() as in the following example:
     */
    @Test
    public void whenConvertListToStringAndSkipNullThenConverted() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").skipNulls().join(names);

        assertEquals(result, "John,Jane,Adam,Tom");
    }

    /**
     * 6. Create List from String using Splitter
     *
     */
    @Test
    public void whenCreateListFromStringThenCreated() {
        String input = "apple - banana - orange";
        List<String> results = Splitter.on("-")
                .trimResults()
                .splitToList(input);

        assertTrue(results.contains("apple"));
        assertTrue(results.contains("banana"));
        assertTrue(results.contains("orange"));
    }

    /**
     * 7. Create Map from String using Splitter
     */
    @Test
    public void whenCreateMapFromStringThenCreated() {
        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(input);

        assertEquals("first", result.get("John"));
        assertEquals("second", result.get("Adam"));
    }

    /**
     * 8. Split String with multiple separators
     */
    @Test
    public void whenSplitStringOnMultipleSeparatorThenSplit() {
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.|,]")
                .omitEmptyStrings()
                .splitToList(input);
        assertTrue(result.contains("apple"));
        assertTrue(result.contains("banana"));
        assertTrue(result.contains("orange"));
    }

    /**
     * 9. Split a String at specific length
     */
    @Test
    public void whenSplitStringOnSpecificLengthThenSplit() {
        String input = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(input);

        assertTrue(result.contains("Hel"));
        assertTrue(result.contains("lo "));
        assertTrue(result.contains("wor"));
        assertTrue(result.contains("ld"));
    }

    /**
     * 10. Limit the split result
     */
    @Test
    public void whenLimitSplittingThenLimited() {
        String input = "a,b,c,d,e";
        List<String> result = Splitter.on(",")
                .limit(4)
                .splitToList(input);
        System.out.println(result.toString());
        assertEquals(4, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("d,e"));
    }

}
