package com.qatraining.steps;

import com.qatraining.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com";

    public LoginSteps(TestContext context) {
        this.driver = context.getDriver();
    }

    @Given("el usuario está en la página de login")
    public void elUsuarioEstaEnLaPaginaDeLogin() {
        driver.get(BASE_URL);
    }

    @When("ingresa el usuario {string} y la contraseña {string}")
    public void ingresaElUsuarioYLaContrasena(String usuario, String contrasena) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(contrasena);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("debería ver la página de productos")
    public void deberiaVerLaPaginaDeProductos() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        String title = driver.findElement(By.className("title")).getText();
        assertEquals("Products", title);
    }

    @Then("debería ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        assertTrue(error.contains(mensajeEsperado));
    }
}