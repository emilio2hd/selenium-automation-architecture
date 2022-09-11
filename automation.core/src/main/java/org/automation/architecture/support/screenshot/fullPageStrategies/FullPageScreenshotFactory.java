package org.automation.architecture.support.screenshot.fullPageStrategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum FullPageScreenshotFactory {
    ChromeDriver {
        @Override
        public IFullPageStrategy getFullPageStrategy(WebDriver chromeDriver) {
            return new ChromeFullPageStrategy((ChromeDriver) chromeDriver);
        }
    },
    FirefoxDriver {
        @Override
        public IFullPageStrategy getFullPageStrategy(WebDriver webDriver) {
            return new FirefoxFullPageStrategy((FirefoxDriver) webDriver);
        }
    };

    public abstract IFullPageStrategy getFullPageStrategy(WebDriver webDriver);
}
