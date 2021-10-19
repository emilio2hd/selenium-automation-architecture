package org.automation.architecture.exceptions;

/**
 * Thrown to indicate that a browser option is invalid.
 */
public class NoWebDriverFactoryImplementedException extends IllegalArgumentException {
    public NoWebDriverFactoryImplementedException(String browser) {
        super(String.format("The browser '%s' has no WebDriver factory implemented", browser));
    }
}
