import client.Session;
import context.ApplicationContext;
import factory.BeanFactory;

import java.io.IOException;


public class Application {
    public ApplicationContext run(){
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        return ApplicationContext.getInstance();
    }
    public static void main(String[] args) throws IOException {
        Application application = new Application();
        ApplicationContext context = application.run();
        Session session = context.getBean(Session.class);
        session.startUp();
    }
}

