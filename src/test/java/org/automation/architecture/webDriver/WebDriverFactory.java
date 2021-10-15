package org.automation.architecture.webDriver;

import org.automation.architecture.TestProperties;
import org.openqa.selenium.WebDriver;

/**
 * Enum that links a browser option with its WebDriver factory
 */
public enum WebDriverFactory {
    CHROME {
        @Override
        public WebDriver getWebDriverInstance(TestProperties testProperties) {
            return ChromeWebDriverFactory.create(testProperties);
        }
    },
    FIREFOX {
        @Override
        public WebDriver getWebDriverInstance(TestProperties testProperties) {
            return FirefoxWebDriverFactory.create(testProperties);
        }
    };

    public abstract WebDriver getWebDriverInstance(TestProperties testProperties);
}
