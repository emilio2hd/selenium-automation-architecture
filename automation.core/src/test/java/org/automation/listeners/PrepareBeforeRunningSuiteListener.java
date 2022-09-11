package org.automation.listeners;

import org.automation.architecture.support.screenshot.ScreenshotDirUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Listener to be used in order to do stuff before running automated tests suite,
 * as this listener will be used by {@link org.automation.BaseTest}.
 */
public class PrepareBeforeRunningSuiteListener implements ISuiteListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareBeforeRunningSuiteListener.class);

    @Override
    public void onStart(final ISuite suite) {
        createScreenshotDirectories();
    }

    private void createScreenshotDirectories() {
        LOGGER.debug("Creating screenshot directories");

        try {
            Files.createDirectories(Paths.get(ScreenshotDirUtility.getElementDirPath()));
            Files.createDirectories(Paths.get(ScreenshotDirUtility.getFailedDirPath()));
            Files.createDirectories(Paths.get(ScreenshotDirUtility.getPageDirPath()));
        } catch (IOException ioe) {
            LOGGER.error("Unable to create screenshot directories.", ioe);
        }
    }
}
