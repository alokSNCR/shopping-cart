package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

import java.util.List;

public interface ICart {
  List<Item> getItems();

  void empty();

  void add(String itemName) throws ItemNotSameTypeException;

  void add(List<String> itemNames) throws ItemNotSameTypeException;

  double calculateSalesTax(double taxRate) throws ItemNotSameTypeException;

  double calculateMarkerPrice() throws ItemNotSameTypeException;

  double calculateTotalPrice(double taxRate) throws ItemNotSameTypeException;
}
