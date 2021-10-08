package org.automation.architecture.support;

import org.automation.architecture.Constants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

/**
 * This class just register {@link SimpleThreadScope} to be used because of parallel execution.
 * BeanFactoryPostProcessor are executed before the creation of objects (Bean), when ApplicationContext
 * container is initialized.
 */
@Component
public class RegisterScopeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(Constants.BEAN_SIMPLE_THREAD_SCOPE, new SimpleThreadScope());
    }
}
