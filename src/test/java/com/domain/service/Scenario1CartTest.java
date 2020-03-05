package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * Step 1: Add products to the shopping cart.
 *
 *Given:
 *    An empty shopping cart and a product, Dove Soap with a unit price of 39.99
 *
 * When:
 *    The user adds 5 Dove Soaps to the shopping cart
 *
 * Then:
 *   The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
 *   And the shopping cart’s total price should equal 199.95
 */
@RunWith(JUnit4.class)
public class Scenario1CartTest {

  private Inventory inventory;
  private List<Item> items = new CopyOnWriteArrayList<Item>();
  private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

  @Before
  public void setUp() {
    items.add(new Item("Dove Soap", 39.99));
    itemPromotions.add(PromotionType.MARKED_PRICE);
    inventory = new Inventory(items, itemPromotions);
  }


  /**
   * The user adds 5 Dove Soaps to the shopping cart
   *
   * @throws ItemNotSameTypeException
   */
  @Test
  public void testScenario2() throws ItemNotSameTypeException {
    // an empty cart
    Cart cart = new Cart(inventory);

    // add 5 Dove soap to the cart
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("Dove Soap", "Dove Soap", "Dove Soap", "Dove Soap", "Dove Soap"));
    cart.add(order);

    // total price should be 199.95d
    assertEquals(cart.calculateMarkerPrice(), 199.95d, 0.01);
  }

  @Test
  public void testEmptyCart() throws ItemNotSameTypeException {
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("Dove Soap"));

    Cart cart = new Cart(inventory);
    cart.add(order);
    cart.empty();

    assertEquals(cart.calculateMarkerPrice(), 0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCatchEmptyAddException() throws ItemNotSameTypeException {
    Cart cart = new Cart(inventory);
    cart.add("");
    cart.empty();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCatchEmptyListOnAddException() throws ItemNotSameTypeException {
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("", ""));
    Cart cart = new Cart(inventory);
    cart.add(order);
    cart.empty();
  }

  @Test
  public void testCreateBasket() throws ItemNotSameTypeException {
    List<String> order = new CopyOnWriteArrayList<>(Arrays.asList("Dove Soap"));
    Cart cart = new Cart(inventory);
    cart.add(order);
    assertNotNull(cart);
    assertTrue(cart.getItems().size() > 0);

  }
}