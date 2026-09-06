package com.qatraining;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

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
    void createContextAndPage() {
        page = browser.newPage();
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @Test
    void loginExitosoConCredencialesValidas() {
        page.navigate(BASE_URL);
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");

        assertTrue(page.url().contains("inventory.html"));
        assertEquals("Products", page.locator(".title").textContent());
    }

    @Test
    void loginFallidoConCredencialesInvalidas() {
        page.navigate(BASE_URL);
        page.fill("#user-name", "usuario_invalido");
        page.fill("#password", "password_incorrecta");
        page.click("#login-button");

        String errorText = page.locator("[data-test='error']").textContent();
        assertTrue(errorText.contains("Username and password do not match"));
    }

    @Test
    void loginFallidoConUsuarioBloqueado() {
        page.navigate(BASE_URL);
        page.fill("#user-name", "locked_out_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");

        String errorText = page.locator("[data-test='error']").textContent();
        assertTrue(errorText.contains("Epic sadface: Sorry, this user has been locked out"));
    }
}