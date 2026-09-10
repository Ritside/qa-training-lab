package com.qatraining;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private final WebDriver driver;

    public TestContext() {
        this.driver = DriverFactory.createDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }
}