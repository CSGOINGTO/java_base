package base_knowledge.rpc.client;

import base_knowledge.rpc.client.net.NetClient;
import base_knowledge.rpc.common.protocol.MessageProtocol;
import base_knowledge.rpc.common.protocol.Request;
import base_knowledge.rpc.common.protocol.Response;
import base_knowledge.rpc.discovery.ServiceInfo;
import base_knowledge.rpc.discovery.ServiceInfoDiscoverer;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Data
public class ClientStubProxyFactory {
    private ServiceInfoDiscoverer serviceInfoDiscoverer;

    private Map<String, MessageProtocol> supportMessageProtocol;

    private NetClient netClient;

    private Map<Class<?>, Object> objectCache = new HashMap<>();

    public <T> T getProxy(Class<?> interfaceClass) {
        T obj = (T) objectCache.get(interfaceClass);
        if (obj == null) {
            obj = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new ClientStubInvocationHandler(interfaceClass));
            objectCache.put(interfaceClass, obj);
        }
        return obj;
    }

    public void setServiceInfoDiscoverer(ServiceInfoDiscoverer serviceInfoDiscoverer) {
        this.serviceInfoDiscoverer = serviceInfoDiscoverer;
    }

    class ClientStubInvocationHandler implements InvocationHandler {

        /**
         * 接口名称作为服务的名称
         */
        private Class<?> interf;

        private final Random random = new Random();

        public ClientStubInvocationHandler(Class<?> interf) {
            super();
            this.interf = interf;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (method.getName().equals("toString")) {
                return proxy.getClass().toString();
            }
            if (method.getName().equals("hashCode")) {
                return proxy.getClass().hashCode();
            }
            final String serviceName = this.interf.getName();
            // 获取服务信息
            final List<ServiceInfo> serviceInfoList = serviceInfoDiscoverer.getServiceInfo(serviceName);
            if (serviceInfoList == null || serviceInfoList.size() == 0) {
                throw new Exception("远程服务不存在！");
            }
            // 随机获取一个服务
            final ServiceInfo serviceInfo = serviceInfoList.get(random.nextInt(serviceInfoList.size()));
            // 构建请求
            Request request = new Request();
            request.setServiceName(serviceName);
            request.setMethod(method.getName());
            request.setParameterTypes(method.getParameterTypes());
            request.setParameters(args);

            // 协议层
            final MessageProtocol protocol = supportMessageProtocol.get(serviceInfo.getProtocol());
            // 编码请求
            final byte[] data = protocol.marshallingRequest(request);
            // 发送网络请求
            final byte[] responseData = netClient.sendRequest(data, serviceInfo);
            // 解码相应
            final Response response = protocol.unmarshallingResponse(responseData);
            // 处理异常
            if (response.getException() != null) {
                throw response.getException();
            }
            return response.getReturnValue();
        }
    }
}
