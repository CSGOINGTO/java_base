package springinaction.chapter_4.concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriticismEngineImpl implements CriticismEngine{

    @Autowired
    private String[] criticismPool;

    @Override
    public String getCriticism() {
        return criticismPool[(int) (Math.random() * criticismPool.length)];
    }
}
