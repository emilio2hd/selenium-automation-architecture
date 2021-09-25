package org.automation.architecture.annotations;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to be used in PageObject classes, in order to
 * be identified by the {@link org.automation.architecture.support.PageObjectBeanPostProcessor} class
 * and initialize the objects using {@link org.openqa.selenium.support.PageFactory#initElements(WebDriver, Object)}.
 *
 * For example:
 * <pre> {@code
 * @PageObject
 * public class SomePage {
 *     @FindBy(id = "someId")
 *     public WebElement someElement;
 * }
 * }</pre>
 */
@Target(TYPE)
@Retention(RUNTIME)
@Component
@Inherited
public @interface PageObject {}