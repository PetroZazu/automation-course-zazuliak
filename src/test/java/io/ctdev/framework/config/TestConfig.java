package io.ctdev.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"classpath:testing.properties"})
public interface TestConfig extends Config {
    TestConfig cfg = ConfigFactory.create(TestConfig.class);

    String remoteUrl();
    String baseUrl();
    String env();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("http://3.18.213.48")
    String juiceShopMainPage();

    @DefaultValue("http://3.18.213.48/#/login")
    String juiceShopLoginPage();

    @DefaultValue("http://3.18.213.48/#/register")
    String juiceShopSignUpPage();

    @DefaultValue("http://3.18.213.48/#/basket")
    String juiceShopBucketPage();

    boolean remote();
}


