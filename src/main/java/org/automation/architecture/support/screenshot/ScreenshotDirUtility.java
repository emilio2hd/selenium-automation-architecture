package org.automation.architecture.support.screenshot;

import java.io.File;
import java.nio.file.Paths;

/**
 * Utilities to get screenshot directory paths.
 */
public class ScreenshotDirUtility {
    private static final String FAILED_TEST_DIR = "failed-test";
    private static final String ELEMENTS_DIR = "elements";
    private static final String PAGES_DIR = "pages";

    public static String getFailedDirPath() {
        return Paths.get(getScreenshotDirPath(), FAILED_TEST_DIR).toString();
    }

    public static String getElementDirPath() {
        return Paths.get(getScreenshotDirPath(), ELEMENTS_DIR).toString();
    }

    public static String getPageDirPath() {
        return Paths.get(getScreenshotDirPath(), PAGES_DIR).toString();
    }

    private static String getScreenshotDirPath() {
        String basePath = new File(getBaseDir()).getAbsolutePath();
        return Paths.get(basePath, "target", "surefire-reports", "screenshots").toString();
    }

    private static String getBaseDir() {
        return System.getProperty("user.dir");
    }
}
