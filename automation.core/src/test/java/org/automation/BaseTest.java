package org.automation;

import org.automation.architecture.Constants;
import org.automation.architecture.support.screenshot.ScreenshotUtility;
import org.automation.architecture.support.screenshot.ScreenshotDirUtility;
import org.automation.listeners.ScreenshotOnFailureListener;
import org.automation.listeners.PrepareBeforeRunningSuiteListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

import java.nio.file.Paths;
import java.util.Objects;

/**
 * This class must be inherited by all test classes.
 * It's responsible for initialize Spring Application Context.
 */
@ContextConfiguration(classes = TestContextConfig.class)
@Listeners({ PrepareBeforeRunningSuiteListener.class, ScreenshotOnFailureListener.class })
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DefaultListableBeanFactory beanFactory;
    @Autowired
    protected WebDriver webDriver;

    /**
     * Due to parallel setting per "classes", each test class will run on a thread and has its own WebDriver instance.
     * The {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#destroyBean(Object)},
     * will tell spring to destroy the bean created for the thread, consequently calling the
     * {@link org.automation.architecture.support.WebDriverFactoryBean#destroy()}.
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        beanFactory.destroyScopedBean(Constants.WEB_DRIVER_BEAN_NAME);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Capture screenshot of a specific element.
     *
     * @param element WebElement which will have the screenshot taken.
     * @param imageName Screenshot image name, saved in the "screenshot/elements".
     *                  No need to specify extension, as it will save it as {@value ScreenshotUtility#DEFAULT_IMAGE_FORMAT}
     */
    public void captureElement(final WebElement element, final String imageName) {
        Objects.requireNonNull(element);

        String elementsScreenshotDir = ScreenshotDirUtility.getElementDirPath();
        String destinationFile = Paths.get(elementsScreenshotDir, String.format("%s.png", imageName)).toString();

        ScreenshotUtility.captureElement(element, destinationFile);
    }

    /**
     * Capture screenshot of current page opened by WebDriver.
     *
     * @param imageName Screenshot image name, saved in the "screenshot/pages".
     *                  No need to specify extension, as it will save it as {@value ScreenshotUtility#DEFAULT_IMAGE_FORMAT}
     * @param isFullPage Set to screenshot of full page.
     */
    public void capturePage(final String imageName, final Boolean isFullPage) {
        String pageScreenshotDir = ScreenshotDirUtility.getPageDirPath();
        String destinationFile = Paths.get(pageScreenshotDir, String.format("%s.png", imageName)).toString();

        ScreenshotUtility.capturePage(getWebDriver(), destinationFile, isFullPage);
    }
}
