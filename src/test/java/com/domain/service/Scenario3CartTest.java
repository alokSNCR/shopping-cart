package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Given:
 * 1) An empty shopping cart
 * 2) And a product, Dove Soap with a unit price of 39.99
 * 3) And another product, Axe Deo with a unit price of 99.99
 * 4) And a sales tax rate of 12.5%
 * <p>
 * <p>
 * When:
 * 1) The user adds 2 Dove Soaps to the shopping cart
 * 2) And then adds 2 Axe Deos to the shopping cart
 * <p>
 * Then:
 * 1) The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
 * 2) And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
 * 3) And the total sales tax amount for the shopping cart should equal 35.00
 * 4) And the shopping cart’s total price should equal 314.96
 */
public class Scenario3CartTest {

  private Inventory inventory;
  private double taxRate;
  private List<Item> items = new CopyOnWriteArrayList<Item>();
  private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

  @Before
  public void setUp() {
    // G
    items.add(new Item("Dove Soap", 39.99));
    itemPromotions.add(PromotionType.MARKED_PRICE);

    items.add(new Item("Axe Deo", 99.99));
    itemPromotions.add(PromotionType.MARKED_PRICE);

    taxRate = 12.5;
    inventory = new Inventory(items, itemPromotions);
  }

  /**
   * The user adds 2 Dove Soaps to the shopping cart
   * And then adds 2 Axe Deos to the shopping cart
   *
   * @throws ItemNotSameTypeException
   */
  @Test
  public void testScenario3() throws ItemNotSameTypeException {
    // an empty cart
    Cart cart = new Cart(inventory);

    // add 2 Dove soap to the cart
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("Dove Soap", "Dove Soap"));
    cart.add(order);

    // add another 2 Axe Deo to the cart
    order = new CopyOnWriteArrayList<>(Arrays.asList("Axe Deo", "Axe Deo"));
    cart.add(order);

    // total sales tax should be 35.00 with the rate of 12.5%
    assertEquals(35.00d, cart.calculateSalesTax(taxRate), 0.01);

    // total price should be 319.92d
    assertEquals(314.96d, cart.calculateTotalPrice(taxRate), 0.01);
  }

  @Test
  public void testEmptyCart() throws ItemNotSameTypeException {
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("Dove Soap"));

    Cart cart = new Cart(inventory);
    cart.add(order);
    cart.empty();

    assertEquals(0, cart.calculateMarkerPrice(), 0.0);
    assertEquals(0d, cart.calculateSalesTax(taxRate), 0.00);
    assertEquals(0d, cart.calculateTotalPrice(taxRate), 0.00);
  }
}
