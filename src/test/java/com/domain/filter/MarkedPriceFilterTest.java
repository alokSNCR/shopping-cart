package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class MarkedPriceFilterTest {

    private MarkedPriceFilter markedPriceFilter;

    @Test
    public void testMarkedPrice() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        List<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Dove Soap", 39.99));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 39.89d, 0.15);
    }

    @Test
    public void testMarkedPriceWithEmptyList() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        List<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testMarkedPriceWithManyNonePromotedItem() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        List<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Axe Deo", 15));
        threadSafeItemList.add(new Item("Axe Deo", 15));
        threadSafeItemList.add(new Item("Axe Deo", 15));
        threadSafeItemList.add(new Item("Axe Deo", 15));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 60.0d, 6.00);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testMarkedPriceWithException() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        List<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Dove Soap", 39.99));
        threadSafeItemList.add(new Item("Axe Deo", 99.99));
        markedPriceFilter.filterPrice(threadSafeItemList);
    }

}