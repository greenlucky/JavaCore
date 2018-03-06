package com.lamdevops.concurrent.async.completionfuture.tut4_ratingcars;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class RatingCard {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        cars().thenCompose(cars -> {
            List<CompletionStage> updateCards = cars.stream()
                    .map(car -> rating(car.manufacturerId).thenApply(r -> {
                        car.setRating(r);
                        return car;
                    })).collect(Collectors.toList());

            CompletableFuture<Void> done = CompletableFuture.allOf(updateCards.toArray(new CompletableFuture[updateCards.size()]));
            return done.thenApply(v -> updateCards.stream()
                    .map(CompletionStage::toCompletableFuture)
                    .map(CompletableFuture::join).collect(Collectors.toList()));
        }).whenComplete((cars, th) -> {
            if(th == null) {
                cars.forEach(System.out::println);
            } else {
                throw new RuntimeException(th);
            }
        }).toCompletableFuture().join();

        long end = System.currentTimeMillis();

        System.out.println("Took " + (end - start) + " ms.");

        start = System.currentTimeMillis();
        List<Car> cars1 = carsA();
        cars1.forEach(car -> {
            float rating = ratingA(car.manufacturerId);
            car.setRating(rating);
        });

        cars1.forEach(System.out::println);
        end = System.currentTimeMillis();

        System.out.println("Took " + (end - start) + " ms.");
    }
    static List<Car> carsA() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, 3, "Fiesta", 2017));
        carList.add(new Car(2, 7, "Camry", 2014));
        carList.add(new Car(3, 2, "M2", 2008));
        return carList;
    }

    static float ratingA(int manufacturer) {
        try {
            simulateDelay();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        switch (manufacturer) {
            case 2:
                return 4f;
            case 3:
                return 4.1f;
            case 7:
                return 4.2f;
            default:
                return 5f;
        }
    }

    private static CompletionStage<Float> rating(int manufacturerId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                simulateDelay();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (manufacturerId) {
                case 2: return 4f;
                case 3: return 4.1f;
                case 7: return 4.2f;
                default: return 5f;
            }
        });
    }
    private static void simulateDelay() throws InterruptedException {
        Thread.sleep(5000);
    }

    private static CompletionStage<List<Car>> cars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, 3, "Fiesta", 2017));
        cars.add(new Car(2, 7, "Camry", 2014));
        cars.add(new Car(3, 2, "M2", 2008));
        return CompletableFuture.supplyAsync(() -> cars);
    }
}
