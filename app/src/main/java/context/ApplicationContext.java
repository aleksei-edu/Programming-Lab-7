package context;

import factory.BeanFactory;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private BeanFactory beanFactory;
    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();
    private final Map<Class,Object> beanMap = new ConcurrentHashMap<>();
    public ApplicationContext(){
    }
    public static ApplicationContext getInstance(){
        return APPLICATION_CONTEXT;
    }
    public <T> T getBean(Class<T> clazz)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(beanMap.containsKey(clazz)){
            return (T) beanMap.get(clazz);
        }
        T bean = beanFactory.getBean(clazz);
        beanMap.put(clazz,bean);
        return bean;
    }
    public <T> T getBean(String className)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<T> clazz = (Class<T>) beanFactory.getClassByClassName(className);
        return APPLICATION_CONTEXT.getBean(clazz);
    }
}
