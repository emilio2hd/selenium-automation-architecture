package org.automation.architecture.webDriver;

import org.openqa.selenium.WebDriver;

/**
 * Enum that links a browser option with its WebDriver factory
 */
public enum WebDriverFactory {
    CHROME {
        @Override
        public WebDriver getWebDriverInstance() {
            return ChromeWebDriverFactory.create();
        }
    },
    FIREFOX {
        @Override
        public WebDriver getWebDriverInstance() {
            return FirefoxWebDriverFactory.create();
        }
    };

    public abstract WebDriver getWebDriverInstance();
}
