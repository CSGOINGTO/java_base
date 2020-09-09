package springinaction.chapter_3.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
public class QAClass implements Project {

    public Project getProject() {
        System.out.println("我是测试环境");
        return new QAClass();
    }
}
