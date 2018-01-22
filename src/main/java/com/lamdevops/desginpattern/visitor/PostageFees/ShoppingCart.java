package com.lamdevops.desginpattern.visitor.PostageFees;

import com.lamdevops.desginpattern.visitor.PostageFees.inf.Visitable;
import com.lamdevops.desginpattern.visitor.PostageFees.model.PostageVisitor;

import java.util.List;

public class ShoppingCart {

    private List<Visitable> items;

    public double calculatePostage() {
        PostageVisitor visitor = new PostageVisitor();
        for (Visitable item : items) {
            item.accept(visitor);
        }

        double postage = visitor.getTotalPostageForCart();
        return postage;
    }

    public void setItems(List<Visitable> items) {
        this.items = items;
    }
}
