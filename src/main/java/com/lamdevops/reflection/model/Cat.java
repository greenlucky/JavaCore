package com.lamdevops.reflection.model;

/**
 * Created by lamdevops on 6/27/17.
 */
public class Cat extends Animal implements Say{

    public static final String SAY = "Meo meo";
    public static final int NUMBER_OF_LEGS = 4;

    private String name;
    public int age;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static String getSAY() {
        return SAY;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String say() {
        return SAY;
    }

    @Override
    public int getNumberOfLegs() {
        return NUMBER_OF_LEGS;
    }
}
