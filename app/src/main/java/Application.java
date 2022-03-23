import client.Session;
import context.ApplicationContext;
import factory.BeanFactory;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Application {
    public ApplicationContext run(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        return ApplicationContext.getInstance();
    }
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        Application application = new Application();
        ApplicationContext context = application.run();
        Session session = context.getBean(Session.class);
        session.startUp();
    }
}

