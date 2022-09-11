package org.automation;

import org.automation.pages.SpringHomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTest extends BaseTest {
    @Autowired
    SpringHomePage springHomePage;

    @Test
    public void shouldAnswerWithTrue() {
        springHomePage.visit();
        assertThat(springHomePage.hero.getText()).contains("Spring");
    }
}
