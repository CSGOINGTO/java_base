package springinaction.chapter_3.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevClass implements Project {

    public Project getProject() {
        System.out.println("我是开发环境");
        return new DevClass();
    }
}
