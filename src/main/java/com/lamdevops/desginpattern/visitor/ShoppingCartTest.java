package com.lamdevops.desginpattern.visitor;

import com.lamdevops.desginpattern.ShoppingCart;
import com.lamdevops.desginpattern.visitor.inf.Visitable;
import com.lamdevops.desginpattern.visitor.model.Book;
import com.lamdevops.desginpattern.visitor.model.CD;
import com.lamdevops.desginpattern.visitor.model.DVD;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {


    @Test
    public void calculatePostage() throws Exception {
        Book book = new Book(7.2, 120);
        CD cd = new CD(2.2, 10);
        DVD dvd = new DVD(3.2, 12);

        List<Visitable> items = Arrays.asList(book, cd, dvd);
        ShoppingCart cart = new ShoppingCart();
        cart.setItems(items);

        double aspectPostagePrice = book.getWeight() * 2 + cd.getWeight() * 1.2 + dvd.getWeight() * 1.5;
        assertEquals(aspectPostagePrice, cart.calculatePostage(), 0.001);
    }

}