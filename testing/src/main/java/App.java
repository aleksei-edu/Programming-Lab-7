import com.lapin.di.config.Configuration;
import com.lapin.di.context.ApplicationContext;
import com.lapin.di.factory.BeanFactory;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        App application = new App();
        ApplicationContext context = application.run();
        Session session = context.getBean(Session.class);
        session.start();
    }

    public ApplicationContext run() {
        BeanFactory beanFactory = new BeanFactory(ApplicationContext.getInstance());
        ApplicationContext.getInstance().setBeanFactory(beanFactory);
        return ApplicationContext.getInstance();
    }
}
