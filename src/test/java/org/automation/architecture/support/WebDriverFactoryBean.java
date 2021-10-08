package org.automation.architecture.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.architecture.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(testProperties.getImplicitlyWaitInMilliseconds(), TimeUnit.MILLISECONDS);

        return driver;
    }

    /**
     * This method calls webdriver quit, in order to close the browser instance.
     *
     * @throws Exception getObject() method could throw exception
     */
    @Override
    public void destroy() throws Exception {
        Objects.requireNonNull(getObject()).quit();
    }
}
