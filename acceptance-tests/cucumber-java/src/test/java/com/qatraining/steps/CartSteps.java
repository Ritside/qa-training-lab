package com.qatraining.steps;

import com.qatraining.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CartSteps {

    private final WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    public CartSteps(TestContext context) {
        this.driver = context.getDriver();
    }

    @Given("el usuario inició sesión correctamente")
    public void elUsuarioInicioSesionCorrectamente() {
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("agrega el producto {string} al carrito")
    public void agregaElProductoAlCarrito(String producto) {
        driver.findElement(By.cssSelector("[data-test='add-to-cart-" + producto + "']")).click();
    }

    @And("remueve el producto {string} del carrito")
    public void removeElProductoDelCarrito(String producto) {
        driver.findElement(By.cssSelector("[data-test='remove-" + producto + "']")).click();
    }

    @Then("el contador del carrito debería mostrar {string}")
    public void elContadorDelCarritoDeberiaMostrar(String cantidadEsperada) {
        String count = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals(cantidadEsperada, count);
    }

    @Then("el contador del carrito debería estar vacío")
    public void elContadorDelCarritoDeberiaEstarVacio() {
        assertFalse(cartBadgeExists());
    }

    private boolean cartBadgeExists() {
        try {
            driver.findElement(By.className("shopping_cart_badge"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}