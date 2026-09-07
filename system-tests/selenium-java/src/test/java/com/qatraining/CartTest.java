package com.qatraining;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

	
    WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
	void setUp() {
		driver = DriverFactory.createDriver();

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