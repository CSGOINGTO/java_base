package design_patterns.observer;

import java.util.Observable;

public class JDKObserverSample {
    public static void main(String[] args) {
        Observable subject1 = new Observable(){
            @Override
            public void notifyObservers(Object arg) {
                //设置 java.util.Observable.changed = true表示发生了改变
                setChanged();
                //调用父类的notifyObservers方法通知观察者发生变化
                //调用链java.util.Observable.notifyObservers(Object)->java.util.Observer.update(Observable, Object)
                super.notifyObservers(arg);
            }
        };
        subject1.addObserver((o, arg) -> System.out.println("观察者1收到通知被更新了。。。" + arg));
        subject1.addObserver((o, arg) -> System.out.println("观察者2收到通知被更新了。。。" + arg));
        subject1.notifyObservers("change1");
        subject1.notifyObservers("change2");
    }
}
