package org.automation.architecture.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.architecture.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirefoxWebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirefoxWebDriverFactory.class);

    public static WebDriver create(final TestProperties testProperties) {
        LOGGER.debug("Using WebDriverManager to setup firefox driver");

        WebDriverManager.firefoxdriver().setup();

        return new FirefoxDriver(getDriverOptions(testProperties.isBrowserHeadless()));
    }

    private static FirefoxOptions getDriverOptions(final boolean isHeadless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-web-security");
        firefoxOptions.addArguments("--allow-running-insecure-content");

        final FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        firefoxOptions.setProfile(profile);

        LOGGER.debug("Setting WebDriver {} ", isHeadless ? "headless" : "to display browser");
        firefoxOptions.setHeadless(isHeadless);

        return firefoxOptions;
    }
}
