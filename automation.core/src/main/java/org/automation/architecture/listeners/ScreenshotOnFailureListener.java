package org.automation.architecture.listeners;


import org.automation.architecture.BaseTest;
import org.automation.architecture.support.screenshot.ScreenshotDirUtility;
import org.automation.architecture.support.screenshot.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Listener to save screenshot when test fails.
 */
public class ScreenshotOnFailureListener implements ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotUtility.class);

    @Override
    public void onTestFailure(final ITestResult result) {
        captureScreenshotOfFailedTest(result);
    }

    private void captureScreenshotOfFailedTest(final ITestResult result) {
        String screenshotFileName =  createScreenshotName(result);

        WebDriver webDriver = ((BaseTest) result.getInstance()).getWebDriver();

        try {
            String destFile = captureScreenshot(webDriver, screenshotFileName);
            Reporter.log("<a href='" + destFile + "'> <img src='" + destFile + "' height='100' width='100'/> </a>");
        } catch (IOException ioe) {
            LOGGER.error("Unable to capture screenshot of current page of failing test '{}'", result.getName(), ioe);
        }
    }

    private String captureScreenshot(final WebDriver webDriver, final String screenshotName) throws IOException {
        String failedDirPath = ScreenshotDirUtility.getFailedDirPath();
        String destinationFile = Paths.get(failedDirPath, String.format("%s.png", screenshotName)).toString();

        ScreenshotUtility.capturePage(webDriver, destinationFile, true);

        return destinationFile;
    }

    /**
     * Create screenshot file name base on test result.
     *
     * @param result TestResult instance
     * @return screenshot name based on test class name and method with datetime. Ex.: SomeTest_myTestMethod_20211026121459.png
     */
    private String createScreenshotName(final ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");

        String currentDate = formatter.format(calendar.getTime());
        String testName = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getName();

        return String.join("_", testName, methodName, currentDate);
    }
}
