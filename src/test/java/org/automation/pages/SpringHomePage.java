package org.automation.pages;

import org.automation.architecture.TestProperties;
import org.automation.architecture.annotations.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

@PageObject
public class SpringHomePage {
    @Autowired
    WebDriver webDriver;
    @Autowired
    TestProperties testProperties;

    @FindBy(id = "hero")
    public WebElement hero;

    public void visit() {
        webDriver.get(testProperties.getAppBaseUrl());
    }
}
