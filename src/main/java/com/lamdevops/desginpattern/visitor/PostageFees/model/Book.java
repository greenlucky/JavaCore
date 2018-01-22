package com.lamdevops.desginpattern.visitor.PostageFees.model;

import com.lamdevops.desginpattern.visitor.PostageFees.abst.Product;
import com.lamdevops.desginpattern.visitor.PostageFees.inf.Visitable;
import com.lamdevops.desginpattern.visitor.PostageFees.inf.Visitor;

public class Book extends Product implements Visitable {

    public Book(double price, double weight) {
        super(price, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
