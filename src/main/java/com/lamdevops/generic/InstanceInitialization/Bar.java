package com.lamdevops.generic.InstanceInitialization;

public abstract class Bar {
    private String name;

    static {
        System.out.println("Bar:static 1");
    }

    {
        System.out.println("Bar:instance 1");
    }

    static {
        System.out.println("Bar: static 2");
    }

    public Bar() {
        System.out.println("Bar:constructor");
    }

    public Bar(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name='" + name + '\'' +
                '}';
    }
}
