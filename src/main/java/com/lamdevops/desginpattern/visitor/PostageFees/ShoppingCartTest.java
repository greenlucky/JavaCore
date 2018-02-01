package com.lamdevops.desginpattern.visitor.PostageFees;

import com.lamdevops.desginpattern.visitor.PostageFees.inf.Visitable;
import com.lamdevops.desginpattern.visitor.PostageFees.model.Book;
import com.lamdevops.desginpattern.visitor.PostageFees.model.CD;
import com.lamdevops.desginpattern.visitor.PostageFees.model.DVD;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {


    @Test
    public void calculatePostage() throws Exception {
        Visitable book = new Book(7.2, 120);
        Visitable cd = new CD(2.2, 10);
        Visitable dvd = new DVD(3.2, 12);

        List<Visitable> items = Arrays.asList(book, cd, dvd);
        ShoppingCart cart = new ShoppingCart();
        cart.setItems(items);

        double aspectPostagePrice = ((Book)book).getWeight() * 2 + ((CD)cd).getWeight() * 1.2 + ((DVD)dvd).getWeight() * 1.5;
        assertEquals(aspectPostagePrice, cart.calculatePostage(), 0.001);
    }

}