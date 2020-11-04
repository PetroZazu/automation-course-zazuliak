package io.ctdev.cucumber.hooks;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class WebDriverHooks {
    @Before
    public void setUp() {
        getDriver().get(TestConfig.cfg.baseUrl());
    }

    @After
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
