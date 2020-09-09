package springinaction.chapter_2.soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CDPlayerConfig {

    @Bean
    public CompactDisc sgtPeppers() {
        System.out.println("我是CDPlayerConfig中的sgtPeppers方法");
        return new SgtPeppers();
    }


    @Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }
}
