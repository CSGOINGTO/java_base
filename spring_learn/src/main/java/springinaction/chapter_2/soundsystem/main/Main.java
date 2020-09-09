package springinaction.chapter_2.soundsystem.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springinaction.chapter_2.soundsystem.CDPlayerConfig;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
        System.out.println(context.getBean("CDPlayer"));
    }
}
