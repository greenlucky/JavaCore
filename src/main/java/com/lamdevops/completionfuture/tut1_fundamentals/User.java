package com.lamdevops.completionfuture.tut1_fundamentals;

public class User {
    private Integer id;
    private String name;
    private double creditRating;
    public User(int i, String s, double rating) {
        this.id = i;
        this.name = s;
        this.creditRating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(double creditRating) {
        this.creditRating = creditRating;
    }
}
