package com.lamdevops.completionfuture.tutorial1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        IntStream.range(1, 1000).forEach(i -> {
            users.add(new User(i, "User name " + i, new Random().nextDouble()));
        });
    }

    public CompletableFuture<User> getUserDetail(final int id) {
        return CompletableFuture.supplyAsync(() -> users.stream().filter(u -> u.getId() == id).findFirst().orElse(null));
    }

    public CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> user.getCreditRating());
    }
}
