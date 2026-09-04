package com.qatraining;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void carritoVacioAlCrear() {
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getTotal());
    }

    @Test
    void agregarItemCalculaTotalCorrecto() {
        cart.addItem("Laptop", 500, 1);
        assertEquals(500, cart.getTotal());
        assertFalse(cart.isEmpty());
    }

    @Test
    void agregarMultiplesItems() {
        cart.addItem("Mouse", 20, 2);
        cart.addItem("Teclado", 50, 1);
        assertEquals(90, cart.getTotal());
        assertEquals(3, cart.itemCount());
    }

    @Test
    void removerItem() {
        cart.addItem("Monitor", 200, 1);
        cart.removeItem("Monitor");
        assertTrue(cart.isEmpty());
    }

    @Test
    void precioNegativoLanzaError() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Producto", -10, 1));
    }

    @Test
    void cantidadCeroLanzaError() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Producto", 10, 0));
    }
}