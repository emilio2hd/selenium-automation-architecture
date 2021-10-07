package org.automation;

import org.automation.architecture.Constants;
import org.automation.architecture.TestProperties;
import org.automation.architecture.support.WebDriverFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(TestProperties.class)
public class TestContextConfig {
    @Autowired
    TestProperties testProperties;

    @Bean(name = Constants.WEB_DRIVER_BEAN_NAME)
    public WebDriverFactoryBean webDriverFactory() {
        return new WebDriverFactoryBean(testProperties);
    }
}
