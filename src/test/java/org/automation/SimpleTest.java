package org.automation;

import org.automation.pages.SpringHomePage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes = TestContextConfig.class)
public class SimpleTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected WebDriver driver;
    @Autowired
    SpringHomePage springHomePage;

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        springHomePage.visit();
        assertThat(springHomePage.hero.getText()).contains("Spring");
    }
}
