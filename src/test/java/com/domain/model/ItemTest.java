package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ItemTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createItem() {
        Item item = new Item("Dove Soap", 39.99);
        assertEquals(item.getName(), "Dove Soap");
        assertEquals(item.getPrice(), 39.99, 0.01);
    }

    @Test
    public void addItem() throws ItemNotSameTypeException {

        Item item = new Item("Axe Deo", 99.99);
        Item newItem = new Item(item);
        newItem.setName("Foo");
        newItem.setPrice(0.1);
        assertEquals(newItem.getName(), "Foo");
        assertEquals(newItem.getPrice(), 0.1, DELTA);

    }
}