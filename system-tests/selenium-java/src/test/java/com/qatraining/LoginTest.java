package com.qatraining;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
	
    WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
	void setUp() {
		driver = DriverFactory.createDriver();
	}

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginExitosoConCredencialesValidas() {
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        WebElement title = driver.findElement(By.className("title"));
        assertEquals("Products", title.getText());
    }

    @Test
    void loginFallidoConCredencialesInvalidas() {
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("usuario_invalido");
        driver.findElement(By.id("password")).sendKeys("password_incorrecta");
        driver.findElement(By.id("login-button")).click();

        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.getText().contains("Username and password do not match"));
    }

    @Test
    void loginFallidoConUsuarioBloqueado() {
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.getText().contains("Epic sadface: Sorry, this user has been locked out"));
    }
}