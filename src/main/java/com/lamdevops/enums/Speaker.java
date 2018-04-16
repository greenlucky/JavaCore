package com.lamdevops.enums;

public interface Speaker {
    void speak();
}

enum Person implements Speaker {

    JOE("Joseph"),
    JIM("James");
    private final String name;

    private Person(String name) {
        this.name = name;
    }

    @Override
    public void speak() {
        System.out.println("Hi " + name);
    }
}

enum Person1 implements Speaker {

    JOE("Joseph") {
        public void speak() {
            System.out.println("Hi, my name is Joseph");
        }
    },
    JIM("James") {
        public void speak() {
            System.out.println("Hey, what's up?");
        }
    };
    private final String name;

    private Person1(String name) {
        this.name = name;
    }

    @Override
    public void speak() {
        System.out.println("Hi " + name);
    }
}
