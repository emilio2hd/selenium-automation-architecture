package org.automation.architecture.support.screenshot;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Utility to capture screenshot of full page or a specific element using aShot,
 * saving it as a {@value ScreenshotUtility#DEFAULT_IMAGE_FORMAT} image.
 */
public class ScreenshotUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotUtility.class);
    public static final String DEFAULT_IMAGE_FORMAT = "png";

    /**
     * Capture screenshot page as {@value ScreenshotUtility#DEFAULT_IMAGE_FORMAT} image..
     *
     * @param webDriver WebDriver instance so aShot can use it to take the screenshot
     * @param screenshotPath Where the full page image screenshot will be saved
     */
    public static void capturePage(final WebDriver webDriver, final String screenshotPath) {
        try {
            File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(screenshotPath));
        } catch (IOException ioe) {
            LOGGER.error("Unable to write screenshot file.", ioe);
        }
    }

    /**
     * Capture a screenshot of a specific element as {@value ScreenshotUtility#DEFAULT_IMAGE_FORMAT} image.
     *
     * @param webElement Element to be captured
     * @param screenshotPath Where the element image will be saved
     */
    public static void captureElement(final WebElement webElement, final String screenshotPath) {
        Objects.requireNonNull(webElement);
        try {
            File scrFile = webElement.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(scrFile, new File(screenshotPath));
        } catch (IOException ioe) {
            LOGGER.error("Unable to write element screenshot file.\n{}", webElement.getText(), ioe);
        }
    }
}
