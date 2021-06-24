package design_patterns.responsibityChain;

public class PlayGame {
    public static void main(String[] args) {
        ResponsibilityChain chain = new ResponsibilityChain();
        chain.register(new ResponsibilityA());
        chain.register(new ResponsibilityB());
        chain.process(() -> System.out.println("Request doSomething..."));
    }
}
