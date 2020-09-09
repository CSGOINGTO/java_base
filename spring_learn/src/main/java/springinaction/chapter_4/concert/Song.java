package springinaction.chapter_4.concert;

import org.springframework.stereotype.Component;

@Component
public class Song implements Performance{
    @Override
    public void perform() {
        System.out.println("歌唱。。。。。。");
    }

    @Override
    public void clapCount(int count) {
        System.out.println(count);
    }
}
