package org.automation.regression_tests.pages;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringHomePage.class);

    @Autowired
    WebDriver webDriver;
    @Autowired
    TestProperties testProperties;

    public void visit() {
        LOGGER.info("Visiting: {}", testProperties.getAppBaseUrl());
        webDriver.get(testProperties.getAppBaseUrl());
    }

    public String getTitle() {
        return webDriver.getTitle();
    }
}
