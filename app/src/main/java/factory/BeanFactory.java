package factory;

import annotation.Inject;
import config.Configuration;
import config.JavaConfiguration;
import configurator.BeanConfigurator;
import configurator.JavaBeanConfigurator;
import context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BeanFactory {
    private final Configuration configuration;
    private final BeanConfigurator beanConfigurator;
    private ApplicationContext applicationContext;
    public BeanFactory(ApplicationContext applicationContext){
        this.configuration = new JavaConfiguration();
        this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()){
            implementationClass = beanConfigurator.getImplementationClass(implementationClass);
        }
        T bean = implementationClass.getDeclaredConstructor().newInstance();
        for (Field field : Arrays.stream(implementationClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).collect(Collectors.toList())){
            field.setAccessible(true);
            field.set(bean,applicationContext.getBean(field.getType()));
        }
        return bean;
    }

    public Class<?> getClassByClassName(String className){
        return beanConfigurator.getImplementationClass(className);
    }

}
