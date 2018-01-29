package com.lamdevops.desginpattern.visitor.PostageFees.abst;

public abstract class Product {

    private double price;
    private double weight;

    public Product(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
