package springinaction.chapter_4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springinaction.chapter_4.concert.Audience;

@Configuration
@ComponentScan(basePackages = "springinaction.chapter_4")
@EnableAspectJAutoProxy
public class ConcertConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    public String[] criticismPool() {
        return new String[]{
                "非常好", "垃圾", "GG"
        };
    }
}
