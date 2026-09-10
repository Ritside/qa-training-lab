package com.qatraining.steps;

import com.qatraining.TestContext;
import io.cucumber.java.After;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @After
    public void tearDown() {
        context.quit();
    }
}