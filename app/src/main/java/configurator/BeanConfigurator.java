package configurator;

public interface BeanConfigurator {
    Class<?> getImplementationClass(String commandName);
    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);
}
