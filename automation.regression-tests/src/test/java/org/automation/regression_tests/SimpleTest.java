package org.automation.regression_tests;

import org.automation.architecture.BaseTest;
import org.automation.regression_tests.pages.SpringHomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTest extends BaseTest {
    @Autowired
    SpringHomePage springHomePage;

    @Test
    public void shouldAnswerWithTrue() {
        springHomePage.visit();
        assertThat(springHomePage.getTitle()).contains("Spring");
    }
}
