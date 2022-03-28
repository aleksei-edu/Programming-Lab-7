package configurator;

import org.reflections.Reflections;

/**
 * Класс ищет реализации заданных объектов
 */
public interface BeanConfigurator {
    Class<?> getImplementationClass(String commandName);

    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    public Reflections getScanner();
}
