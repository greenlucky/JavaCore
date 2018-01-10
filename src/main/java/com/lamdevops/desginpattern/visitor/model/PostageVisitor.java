package com.lamdevops.desginpattern.visitor.model;

import com.lamdevops.desginpattern.visitor.inf.Visitor;

public class PostageVisitor implements Visitor {

    private double totalPostageForCart;

    @Override
    public void visit(Book book) {
        if(book.getPrice() < 10.0) {
            totalPostageForCart += book.getWeight() * 2;
        }
    }

    @Override
    public void visit(CD cd) {
        if(cd.getPrice() < 10.0) {
            totalPostageForCart += cd.getWeight() * 1.2;
        }
    }

    @Override
    public void visit(DVD dvd) {
        if(dvd.getPrice() < 10.0) {
            totalPostageForCart += dvd.getWeight() * 1.5;
        }
    }

    public double getTotalPostageForCart() {
        return totalPostageForCart;
    }
}
