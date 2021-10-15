package org.automation.architecture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class TestProperties {
    @Value("${test.appBaseUrl}")
    private String appBaseUrl;

    @Value("${test.implicitlyWaitInMilliseconds}")
    private Long implicitlyWaitInMilliseconds;

    @Value("${test.defaultBrowseOption}")
    private String defaultBrowseOption;

    public String getAppBaseUrl() {
        return appBaseUrl;
    }

    public String getBrowser() {
        return System.getProperty(Constants.BROWSER_OPTION_SYS_PROPERTY_KEY, defaultBrowseOption);
    }

    public Boolean isBrowserHeadless() {
        return Boolean.getBoolean(Constants.BROWSER_HEADLESS_SYS_PROPERTY_KEY);
    }

    public Long getImplicitlyWaitInMilliseconds() {
        return implicitlyWaitInMilliseconds;
    }
}
