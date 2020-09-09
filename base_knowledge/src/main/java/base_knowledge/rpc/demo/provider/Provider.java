package base_knowledge.rpc.demo.provider;

import base_knowledge.rpc.common.protocol.impl.JavaSerializeMessageProtocol;
import base_knowledge.rpc.demo.DemoService;
import base_knowledge.rpc.server.*;
import base_knowledge.rpc.utils.PropertiesUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(PropertiesUtils.getProperties("rpc.port"));

        final String protocol = PropertiesUtils.getProperties("rpc.protocol");

        // 服务注册
        final ServiceRegister serviceRegister = new ZkExportServiceRegister(PropertiesUtils.getProperties("zk.address"));
        final DemoService demoService = new DemoServiceImpl();
        final ServiceObject serviceObject = new ServiceObject(DemoService.class.getName(), DemoService.class, demoService);
        serviceRegister.register(serviceObject, protocol, port);

        RequestHandler requestHandler = new RequestHandler(new JavaSerializeMessageProtocol(), serviceRegister);

        RpcServer server = new NettyRpcService(port, protocol, requestHandler);

        server.start();
        System.in.read();
        server.stop();
    }
}
