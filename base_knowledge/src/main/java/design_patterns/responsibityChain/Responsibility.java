package design_patterns.responsibityChain;

/**
 * 责任接口
 */
public interface Responsibility {

    /**
     * 处理完自身逻辑之后，调用责任链中的处理方法，用于触发责任链中下一个责任的执行。
     * request是本次责任链的所有者，可以在责任中执行其中的方法。
     */
    void process(Request request, ResponsibilityChain chain);
}
