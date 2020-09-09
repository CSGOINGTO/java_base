package springinaction.chapter_4.concert;

import org.aspectj.lang.annotation.*;

@Aspect
public class Audience {

    @Pointcut("execution(* springinaction.chapter_4.concert.Performance.perform(..))")
    public void performance() {
    }

    @Pointcut("execution(* springinaction.chapter_4.concert.Performance.clapCount(int)) && args(count)")
    public void clapCount(int count) {
    }

    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    @AfterReturning(value = "clapCount(count)", argNames = "count")
    public void applause(int count) {
        System.out.println("新增鼓掌数：" + count);
        System.out.println("CLAP CLAP CLAP！！！");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

    /*
    环绕通知，注意要调用joinPoint的proceed()，不然会阻塞后面的方法的执行
     */
//    @Around("performance()")
//    public void watchPerformance(ProceedingJoinPoint joinPoint) {
//        try {
//            System.out.println("Silencing cell phones");
//            System.out.println("Taking seats");
//            joinPoint.proceed();
//            System.out.println("CLAP CLAP CLAP!!!");
//        } catch (Exception e) {
//            System.out.println("Demanding a refund");
//            e.printStackTrace();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
}
