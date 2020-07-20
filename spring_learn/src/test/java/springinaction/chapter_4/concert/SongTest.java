package springinaction.chapter_4.concert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springinaction.chapter_4.ConcertConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class SongTest {

    @Autowired
    Performance performance;

    @Autowired
    CriticismEngine criticismEngine;

    @Test
    public void testSong() {
        System.out.println(performance);
        performance.clapCount(1);
    }

    @Test
    public void testAspectJ() {
        System.out.println(criticismEngine.getCriticism());
        performance.perform();
    }
}