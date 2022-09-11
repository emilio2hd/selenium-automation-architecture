package org.automation.architecture.support.screenshot.fullPageStrategies;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Collections;
import java.util.Map;

/**
 * Use Chrome developer tools to capture full page screenshot.
 * Based on @see <a href="https://github.com/puppeteer/puppeteer/blob/main/src/common/Page.ts">puppeteer repo</a>.
 */
public class ChromeFullPageStrategy implements IFullPageStrategy {
    private static final String GET_LAYOUT_METRICS_CMD = "Page.getLayoutMetrics";
    private static final String CAPTURE_SCREENSHOT_CMD = "Page.captureScreenshot";

    private final ChromeDriver chromeDriver;

    public ChromeFullPageStrategy(final ChromeDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    @SuppressWarnings("unchecked")
    public File takeFullPageScreenshot() {
        Map<String, Object> metrics = chromeDriver.executeCdpCommand(
                GET_LAYOUT_METRICS_CMD,
                Collections.emptyMap()
        );
        Map<String, Long> contentSize = (Map<String, Long>) (metrics.containsKey("cssContentSize")
                ? metrics.get("cssContentSize")
                : metrics.get("contentSize"));
        Long width = contentSize.get("width");
        Long height = contentSize.get("height");

        Map<String, Object> clip = Map.of(
                "x", 0, "y", 0, "width", width, "height", height, "scale", 1
        );

        Map<String, Object> screenshotResult = chromeDriver.executeCdpCommand(
                CAPTURE_SCREENSHOT_CMD,
                Map.of("clip", clip, "captureBeyondViewport", true)
        );

        String base64 = (String) screenshotResult.get("data");
        return OutputType.FILE.convertFromBase64Png(base64);
    }
}
