import com.lx.config.HelloServiceAutoConfiguration;
import com.lx.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloServiceAutoConfiguration.class)
public class MySpringBootTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void testService() {
        System.out.println(helloService.sayHello());
    }
}
