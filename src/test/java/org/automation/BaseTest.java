package org.automation;

import org.automation.architecture.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;

/**
 * This class must be inherited by all test classes.
 * It's responsible for initialize Spring Application Context.
 */
@ContextConfiguration(classes = TestContextConfig.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DefaultListableBeanFactory beanFactory;

    /**
     * Due to parallel setting per "classes", each test class will run on a thread and has its own webdriver instance.
     * The {@link org.springframework.beans.factory.support.DefaultListableBeanFactory#destroyBean(Object)},
     * will tell spring to destroy the bean created for the thread, consequently calling the
     * {@link org.automation.architecture.support.WebDriverFactoryBean#destroy()}.
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        beanFactory.destroyScopedBean(Constants.WEB_DRIVER_BEAN_NAME);
    }
}
