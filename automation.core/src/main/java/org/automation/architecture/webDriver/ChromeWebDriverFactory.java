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

    public static WebDriver create(final TestProperties testProperties) {
        LOGGER.debug("Using WebDriverManager to setup chrome driver.");

        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(getDriverOptions(testProperties.isBrowserHeadless()));
    }

    private static ChromeOptions getDriverOptions(final boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();

        /*
          Disables bubble notification about running development, disables the password saving UI,
          disables infobar animations, etc.
          Check https://github.com/GoogleChrome/chrome-launcher/blob/master/docs/chrome-flags-for-tools.md#test--debugging-flags
         */
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");

        LOGGER.debug("Setting WebDriver {} ", isHeadless ? "headless" : "to display browser");
        options.setHeadless(isHeadless);

        return options;
    }
}
