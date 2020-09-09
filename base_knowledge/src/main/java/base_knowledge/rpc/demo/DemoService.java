package base_knowledge.rpc.demo;

import java.awt.*;

public interface DemoService {
    String sayHello(String name);

    Point multiPoint(Point p, int multi);
}
