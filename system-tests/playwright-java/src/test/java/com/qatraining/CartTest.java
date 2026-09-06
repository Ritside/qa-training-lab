package com.qatraining;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    static Playwright playwright;
    static Browser browser;
    Page page;

    private static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void loginBeforeEachTest() {
        page = browser.newPage();
        page.navigate(BASE_URL);
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @Test
    void agregarProductoActualizaContador() {
        assertEquals(0, page.locator(".shopping_cart_badge").count());

        page.click("[data-test='add-to-cart-sauce-labs-backpack']");

        assertEquals("1", page.locator(".shopping_cart_badge").textContent());
    }

    @Test
    void agregarMultiplesProductosSumaCorrectamente() {
        page.click("[data-test='add-to-cart-sauce-labs-backpack']");
        page.click("[data-test='add-to-cart-sauce-labs-bike-light']");

        assertEquals("2", page.locator(".shopping_cart_badge").textContent());
    }

    @Test
    void removerProductoActualizaContador() {
        page.click("[data-test='add-to-cart-sauce-labs-backpack']");
        page.click("[data-test='remove-sauce-labs-backpack']");

        assertEquals(0, page.locator(".shopping_cart_badge").count());
    }
}