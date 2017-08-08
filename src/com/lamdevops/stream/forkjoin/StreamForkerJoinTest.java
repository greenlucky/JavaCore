package com.lamdevops.stream.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

/**
 * Created by lamdevops on 8/7/17.
 */
public class StreamForkerJoinTest {

    @Test
    public void testDishProcessMenu() throws Exception {
        long start = System.nanoTime();
        Stream<Dish> menuStream = Dish.menu.stream();

        StreamForker.Results results = new StreamForker<Dish>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(
                        reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                        .get())
                .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMenu);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishByType);
        long end = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Total time: " + end + " msecs");
    }

    @Test
    public void testDishMenuByStream() throws Exception {
        long start = System.nanoTime();
        Supplier<Stream<Dish>> menuStream = () -> Dish.menu.stream();

        String shortMenu = menuStream.get().map(Dish::getName).collect(joining(", "));
        int totalCalories = menuStream.get().mapToInt(Dish::getCalories).sum();
        Dish mostCaloricDish = menuStream.get().collect(
                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();

        Map<Dish.Type, List<Dish>> dishByType = menuStream.get().collect(groupingBy(Dish::getType));

        System.out.println("Short menu: " + shortMenu);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dish by type: " + dishByType);

        long end = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Total time: " + end + " msecs");
    }

}
