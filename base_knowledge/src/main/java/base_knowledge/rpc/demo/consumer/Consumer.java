package base_knowledge.rpc.demo.consumer;

import base_knowledge.rpc.client.ClientStubProxyFactory;
import base_knowledge.rpc.client.net.impl.NettyNetClient;
import base_knowledge.rpc.common.protocol.MessageProtocol;
import base_knowledge.rpc.common.protocol.impl.JavaSerializeMessageProtocol;
import base_knowledge.rpc.demo.DemoService;
import base_knowledge.rpc.discovery.impl.ZookeeperServiceInfoDiscoverer;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        final ClientStubProxyFactory clientStubProxyFactory = new ClientStubProxyFactory();
        clientStubProxyFactory.setServiceInfoDiscoverer(new ZookeeperServiceInfoDiscoverer());
        Map<String, MessageProtocol> supportMessageProtocols = new HashMap<>(2);
        supportMessageProtocols.put("javas", new JavaSerializeMessageProtocol());
        clientStubProxyFactory.setSupportMessageProtocol(supportMessageProtocols);

        clientStubProxyFactory.setNetClient(new NettyNetClient());

        final DemoService demoService = clientStubProxyFactory.getProxy(DemoService.class);
        System.out.println(demoService.sayHello("world!"));
//        System.out.println(demoService.multiPoint(new Point(5, 10), 2));
    }
}
