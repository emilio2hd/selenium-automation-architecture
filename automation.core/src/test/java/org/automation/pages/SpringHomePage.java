package org.automation.pages;

import org.automation.SimpleTest;
import org.automation.architecture.TestProperties;
import org.automation.architecture.annotations.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@PageObject
public class SpringHomePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTest.class);

    @Autowired
    WebDriver webDriver;
    @Autowired
    TestProperties testProperties;

    @FindBy(id = "hero")
    public WebElement hero;

    public void visit() {
        LOGGER.info("Visiting: {}", testProperties.getAppBaseUrl());
        webDriver.get(testProperties.getAppBaseUrl());
    }
}
