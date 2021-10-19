package org.automation.architecture.support;

import org.automation.architecture.annotations.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Class responsible for initializing the beans annotated with {@link org.automation.architecture.annotations.PageObject}
 * passing them to {@link org.openqa.selenium.support.PageFactory#initElements(WebDriver, Object)}, in order to resolve
 * the web elements annotated with {@link org.openqa.selenium.support.FindBy}.
 */
@Component
@Order(0)
public class PageObjectBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageObjectBeanPostProcessor.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @Nullable String beanName) {
        if (bean.getClass().isAnnotationPresent(PageObject.class)) {
            LOGGER.debug("Initializing page object {}", bean);
            PageFactory.initElements(applicationContext.getBean(WebDriver.class), bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @Nullable String beanName) {
        return bean;
    }
}
