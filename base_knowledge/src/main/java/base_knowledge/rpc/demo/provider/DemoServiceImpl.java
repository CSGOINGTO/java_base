package base_knowledge.rpc.demo.provider;

import base_knowledge.rpc.demo.DemoService;

import java.awt.*;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public Point multiPoint(Point p, int multi) {
        return null;
    }
}
