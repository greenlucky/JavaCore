package com.lamdevops.stream.groupby;

import com.lamdevops.stream.pojo.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.lamdevops.stream.filter.FilterTest.print;

/**
 * Created by lam.nm on 7/26/2017.
 */
public class GroupByItem
{
    @Test
    public void countElement() throws Exception {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> finalMap = new LinkedHashMap<>();

        result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
    }

    @Test
    public void countGroupByPojo() throws Exception {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orange", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        print(items.stream());

        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting())
        );
        System.out.println(counting);

        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty))
        );

        System.out.println(sum);
    }

    @Test
    public void groupByPrice() throws Exception {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orange", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        Map<BigDecimal, List<Item>> groupByPriceMap = items.stream()
                .collect(Collectors.groupingBy(Item::getPrice));
        System.out.println(groupByPriceMap);

        Map<BigDecimal, Set<String>> result = items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));

        System.out.println(result);
    }
}
