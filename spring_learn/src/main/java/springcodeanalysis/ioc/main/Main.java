package springcodeanalysis.ioc.main;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springcodeanalysis.ioc.MessageService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml", "classpath:application1.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        System.out.println(defaultListableBeanFactory);
        final MessageService messageService = context.getBean(MessageService.class);
        System.out.println(messageService);
        System.out.println(MessageService.class);
    }
}
