package io.ctdev.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:testing.properties"})
public interface TestConfig extends Config {
    TestConfig cfg = ConfigFactory.create(TestConfig.class);

    @DefaultValue("firefox")
    String browser();

    @DefaultValue("http://18.217.145.6")
    String juiceShopMainPage();

    @DefaultValue("http://18.217.145.6/#/login")
    String juiceShopLoginPage();
}


