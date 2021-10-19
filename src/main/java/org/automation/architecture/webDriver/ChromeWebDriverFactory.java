package org.automation.architecture.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.architecture.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeWebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChromeWebDriverFactory.class);

    public static WebDriver create(TestProperties testProperties) {
        LOGGER.debug("Using WebDriverManager to setup chrome driver.");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        LOGGER.debug("Setting WebDriver {} ", testProperties.isBrowserHeadless() ? "headless" : "to display browser");
        options.setHeadless(testProperties.isBrowserHeadless());

        return new ChromeDriver(options);
    }
}
