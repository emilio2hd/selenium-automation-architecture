package org.automation.architecture.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.architecture.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirefoxWebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirefoxWebDriverFactory.class);

    public static WebDriver create(TestProperties testProperties) {
        LOGGER.debug("Using WebDriverManager to setup firefox driver");

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");

        LOGGER.debug("Setting WebDriver {} ", testProperties.isBrowserHeadless() ? "headless" : "to display browser");
        firefoxOptions.setHeadless(testProperties.isBrowserHeadless());

        return new FirefoxDriver(firefoxOptions);
    }
}
