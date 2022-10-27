package com.qf.commons.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * SpringIOC的工具类
 * 1、从IOC容器中获取指定类型的Bean
 * 2、将任意一个对象注册到IOC容器中（跟@Component @Bean 注解效果是完全一样的）
 * <p>
 * author 14526
 * create_time 2022/10/23
 */
@Component
public class ApplicationUtils implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private static BeanDefinitionRegistry registry;

    //静态IOC容器引用
    private static ApplicationContext applicationContext;

    /**
     * 通过bean的类型，从IOC容器中获取bena对象
     *
     * @param beanClas
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClas) {
        return applicationContext.getBean(beanClas);
    }

    /**
     * 手动注册Bean对象
     */
    public static void registerBean(String beanName, Object bean) {

        //将自定义的对象，包装成BeanDefinition对象
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(bean.getClass(), new Supplier() {
            @Override
            public Object get() {
                return bean;
            }
        });

        //注册Bean对象
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ApplicationUtils.registry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtils.applicationContext = applicationContext;
    }
}
