package springinaction.chapter_4.concert;

import org.springframework.beans.factory.annotation.Autowired;

/*
 使用Spring容器加载时，需要设置factory-method="aspectof"。
 所有的AspectJ切面都提供了一个静态的aspectof()方法，这个方法返回切面的一个单例。
 所以为了获得切面的实例，我们必须使用factory-method来调用aspectof()方法。
 */
public aspect CriticAspect {
    public CriticAspect() {
    }

    @Autowired
    private CriticismEngine criticismEngine;

    pointcut performance(): execution(* springinaction.chapter_4.concert.Performance.perform(..));

    after() returning : performance() {
        System.out.println(criticismEngine.getCriticism());
    }

}
