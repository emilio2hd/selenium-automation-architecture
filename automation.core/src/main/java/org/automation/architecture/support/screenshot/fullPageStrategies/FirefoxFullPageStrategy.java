package org.automation.architecture.support.screenshot.fullPageStrategies;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * Firefox implements full page screenshot, nothing to do here besides using it.
 */
public class FirefoxFullPageStrategy implements IFullPageStrategy {
    private final FirefoxDriver firefoxDriver;

    public FirefoxFullPageStrategy(final FirefoxDriver firefoxDriver) {
        this.firefoxDriver = firefoxDriver;
    }

    public File takeFullPageScreenshot() {
        return firefoxDriver.getFullPageScreenshotAs(OutputType.FILE);
    }
}
