package com.lamdevops.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by lam.nm on 7/17/2017.
 */
public class HandleList {

    @Test
    public void sortListString() throws Exception {
        List<String> lstNames = Arrays.asList("Join", "Marry", "Stack", "Steel", "Harry");
        lstNames.sort((String::compareToIgnoreCase));
        lstNames.forEach(System.out::println);
    }

    @Test
    public void removeItem() throws Exception {
        List<String> lstNames = Arrays.asList("Join", "Marry", "Stack", "Steel", "Harry");
        lstNames.removeIf(i -> i == null);
        lstNames.forEach(System.out::println);
    }

    @Test
    public void toArrayTest() throws Exception {
        List<String> lstNames = Arrays.asList("Join", "Marry", "Stack", "Steel", "Harry");
        String[] lstTemp = lstNames.toArray(new String[lstNames.size()]);
        String[] lstTemp1 = lstNames.stream().toArray(String[]::new);
    }

    @Test
    public void testComparable() throws Exception {
        List<String> lstNames = Arrays.asList("Join", "Marry", "Stack", "Steel", "Harry");
        lstNames.sort(Comparator.reverseOrder());
        lstNames.forEach(System.out::println);
    }

    @Test
    public void testReplaceList() throws Exception {
        List<Integer> lstInts = Arrays.asList(1, 2, 3, 4, 5);
        lstInts.replaceAll(i -> i * 2);
        System.out.println(lstInts);
    }
}
