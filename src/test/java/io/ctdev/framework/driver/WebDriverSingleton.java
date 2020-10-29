package io.ctdev.framework.driver;

import io.ctdev.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {

    }

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            switch (TestConfig.cfg.browser()) {
                case "firefox": {
                    if (TestConfig.cfg.remote()) {
                        try {
                            System.out.println("Firefox browser loaded remote successfully");
                            DesiredCapabilities capabilities = new DesiredCapabilities();
                            capabilities.setCapability("browserName", "firefox");
                            capabilities.setCapability("browserVersion", "81.0");
                            capabilities.setCapability("enableVnc", true);
                            driver.set(new RemoteWebDriver(new URL(TestConfig.cfg.remoteUrl()), capabilities));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new FirefoxDriver());
                        break;
                    }
                    break;
                }
                default: {
                    if (TestConfig.cfg.remote()) {
                        try {
                            System.out.println("Chrome browser loaded remote successfully");
                            DesiredCapabilities capabilities = new DesiredCapabilities();
                            capabilities.setCapability("browserName", "chrome");
                            capabilities.setCapability("browserVersion", "86.0");
                            capabilities.setCapability("enableVnc", true);
                            driver.set(new RemoteWebDriver(new URL(TestConfig.cfg.remoteUrl()), capabilities));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                    }
                }
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            System.out.println("Closing test web driver!");
            //driver.get().close();
            driver.get().quit();
            driver.remove();
        }


    }
}
