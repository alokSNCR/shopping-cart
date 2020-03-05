Feature: Shopping Cart Test

  Scenario: Add products to the shopping cart.
    Given 1) An empty shopping cart and a product,
          2) Dove Soap with a unit price of 39.99
    When The user adds 5 Dove Soaps to the shopping cart
    Then  1) The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
          2) and the shopping cart’s total price should equal 199.95

  Scenario: Add additional products of the same type to the shopping cart.
    Given 1) An empty shopping cart and a product,
          2) Dove Soap with a unit price of 39.99
    When The user adds 5 Dove Soaps to the shopping cart and then adds another 3 Dove Soaps to the shopping cart
    Then  1) The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
          2) And the shopping cart’s total price should equal 319.92

  Scenario: Calculate the tax rate of the shopping cart with multiple items
    Given 1) An empty shopping cart
          2) a product, Dove Soap with a unit price of 39.99
          3) another product, Axe Deo with a unit price of 99.99
          4) a sales tax rate of 12.5%
    When 1) The user adds 2 Dove Soaps to the shopping cart
         2) then adds 2 Axe Deos to the shopping cart
    Then  1) The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
          2)And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
          3) And the total sales tax amount for the shopping cart should equal 35.00
          4) And the shopping cart’s total price should equal 314.96