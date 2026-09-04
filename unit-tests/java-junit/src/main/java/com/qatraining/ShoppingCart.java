package com.qatraining;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static class Item {
        String name;
        double price;
        int quantity;

        Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    private final List<Item> items = new ArrayList<>();

    public void addItem(String name, double price, int quantity) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        items.add(new Item(name, price, quantity));
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.name.equals(name));
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.price * item.quantity)
                .sum();
    }

    public int itemCount() {
        return items.stream().mapToInt(item -> item.quantity).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}