package com.qatraining;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

	
    WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
    void setUp() {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.password_manager_leak_detection", false); // Disables breach popup
		prefs.put("credentials_enable_service", false);             // Disables "Save password" prompts
		prefs.put("profile.password_manager_enabled", false);         // Completely disables password manager

		options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    private boolean cartBadgeExists() {
        try {
            driver.findElement(By.className("shopping_cart_badge"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test
    void agregarProductoActualizaContador() {
        assertFalse(cartBadgeExists());

        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();

        String count = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", count);
    }

    @Test
    void agregarMultiplesProductosSumaCorrectamente() {
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']")).click();

        String count = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("2", count);
    }

    @Test
    void removerProductoActualizaContador() {
        driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("[data-test='remove-sauce-labs-backpack']")).click();

        assertFalse(cartBadgeExists());
    }
}