package org.automation.architecture.support;

import com.google.common.base.Enums;
import com.google.common.base.Optional;
import org.automation.architecture.TestProperties;
import org.automation.architecture.exceptions.NoWebDriverFactoryImplementedException;
import org.automation.architecture.webDriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * When a {@link WebDriver} instance is required by Spring, this class will be responsible for providing it.
 * It will check the {@link System#getProperty(String, String)} for a browser option from
 * {@link TestProperties#getBrowser().
 */
public class WebDriverFactoryBean extends AbstractFactoryBean<WebDriver> implements DisposableBean {
    private final TestProperties testProperties;

    public WebDriverFactoryBean(TestProperties testProperties) {
        super();
        this.testProperties = testProperties;
    }

    @Override
    public Class<?> getObjectType() {
        return WebDriver.class;
    }

    @Override
    protected WebDriver createInstance() throws Exception {
        WebDriver driver = getWebDriverFactory().getWebDriverInstance(testProperties);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(testProperties.getImplicitlyWaitInMilliseconds(), TimeUnit.MILLISECONDS);

        return driver;
    }

    /**
     * This method calls WebDrive quit, in order to close the browser instance.
     *
     * @throws Exception getObject() method could throw exception
     */
    @Override
    public void destroy() throws Exception {
        Objects.requireNonNull(getObject()).quit();
    }

    /**
     * Based on a browser option, get the WebDrive factory from {@link WebDriverFactory} if it exists in the Enum.
     *
     * @return WebDriverFactory is responsible for creating a WebDrive instance
     * @throws NoWebDriverFactoryImplementedException if the browser option is not supported
     */
    private WebDriverFactory getWebDriverFactory() throws NoWebDriverFactoryImplementedException {
        String browserOption = testProperties.getBrowser();

        Optional<WebDriverFactory> webDriverFactory = Enums.getIfPresent(WebDriverFactory.class, browserOption.toUpperCase());
        if(!webDriverFactory.isPresent()) {
            throw new NoWebDriverFactoryImplementedException(browserOption);
        }

        return webDriverFactory.get();
    }
}
