package factory;

import annotation.Inject;
import config.Configuration;
import config.JavaConfiguration;
import configurator.BeanConfigurator;
import configurator.JavaBeanConfigurator;
import context.ApplicationContext;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Фабрика бинов
 */
public class BeanFactory {
    private final Configuration configuration;
    @Getter
    private final BeanConfigurator beanConfigurator;
    private ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        this.configuration = new JavaConfiguration();
        this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementationClass(implementationClass);
        }
        T bean = null;
        try {
            bean = implementationClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            System.err.println("Не удалось создать экземпляр");
        } catch (IllegalAccessException e) {
            System.err.println("Нет доступа к конструктору");
        } catch (InvocationTargetException e) {
            System.err.println("InvocationTargetException");
        } catch (NoSuchMethodException e) {
            System.err.println("Конструктор не найден");
        }
        for (Field field : Arrays.stream(implementationClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).collect(Collectors.toList())) {
            field.setAccessible(true);
            try {
                field.set(bean, applicationContext.getBean(field.getType()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    public Class<?> getClassByClassName(String className) throws Exception {
        return beanConfigurator.getImplementationClass(className);
    }

}
