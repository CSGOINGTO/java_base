package springinaction.chapter_3.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DevClass.class, QAClass.class})
//@ActiveProfiles("qa")
@ActiveProfiles("dev")
public class ProfileTest {

    @Autowired
    Project project;

    @Test
    public void testProfile() {
        project.getProject();
    }
}
