package org.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(classes = TestContextConfig.class)
public class SimpleTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected WebDriver driver;

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        driver.get("https://spring.io");
        assertThat(driver.findElement(By.id("hero")).getText()).contains("Spring");
    }
}
