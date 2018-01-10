package com.lamdevops.desginpattern.visitor.model;

import com.lamdevops.desginpattern.visitor.abst.Product;
import com.lamdevops.desginpattern.visitor.inf.Visitable;
import com.lamdevops.desginpattern.visitor.inf.Visitor;

public class DVD extends Product implements Visitable {

    public DVD(double price, double weight) {
        super(price, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
