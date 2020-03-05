package com.domain.model;

import com.domain.filter.MarkedPriceFilter;
import com.domain.filter.PromotionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class InventoryTest {

    private CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<Item>();
    private CopyOnWriteArrayList<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Test
    public void testCreateInventory() {
        items.add(new Item("Dove Soap", 39.99));
        itemPromotions.add(PromotionType.MARKED_PRICE);

        items.add(new Item("Axe Deo", 99.99));
        itemPromotions.add(PromotionType.MARKED_PRICE);

        Inventory inventory = new Inventory(items, itemPromotions);
        assertNotNull(inventory);

        assertNotNull(inventory.getFilter());
        assertTrue(inventory.getFilter().get("Dove Soap") instanceof MarkedPriceFilter);
        assertTrue(inventory.getFilter().get("Axe Deo") instanceof MarkedPriceFilter);

        assertNotNull(inventory.getListingItems());
        assertEquals(inventory.getListingItems().get("Dove Soap").getName(), "Dove Soap");
        assertEquals(inventory.getListingItems().get("Dove Soap").getPrice(), 39.99d, 0.01);

        assertEquals(inventory.getListingItems().get("Axe Deo").getName(), "Axe Deo");
        assertEquals(inventory.getListingItems().get("Axe Deo").getPrice(), 99.99d, 0.01);

        assertNotNull(inventory.getPromotions());
        assertEquals(inventory.getPromotions().get("Dove Soap"), PromotionType.MARKED_PRICE);
        assertEquals(inventory.getPromotions().get("Axe Deo"), PromotionType.MARKED_PRICE);
    }
}
