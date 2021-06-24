package design_patterns.responsibityChain;

public class ResponsibilityA implements Responsibility{

    @Override
    public void process(Request request, ResponsibilityChain chain) {
        System.out.println("Before Responsibility-A done something...");
        request.doSomething();
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
         * ResponsibilityA处理完以后调用ResponsibilityChain的process方法交给下一个责任逻辑处理 * *
         * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
         * */
        chain.process(request);
    }
}
