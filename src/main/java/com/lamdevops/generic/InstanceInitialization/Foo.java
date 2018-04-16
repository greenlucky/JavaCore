package com.lamdevops.generic.InstanceInitialization;

public class Foo extends Bar{

    private String name;

    {
        System.out.println("Foo:instance 1");
    }

    static {
        System.out.println("Foo:static 1");
    }

    static {
        System.out.println("Foo:static 2");
    }

    public Foo() {
        System.out.println("Foo:constructor");
    }

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Foo withName(String name) {
        return new Foo(name);
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                '}';
    }
}
