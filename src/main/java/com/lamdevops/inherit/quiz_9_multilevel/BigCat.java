package com.lamdevops.inherit.quiz_9_multilevel;

public class BigCat extends WildAnimal {

    public BigCat() {
        this("Jaguar");
    }

    BigCat(String s) {
        super(s);
        System.out.print(s + " ");
    }
}
