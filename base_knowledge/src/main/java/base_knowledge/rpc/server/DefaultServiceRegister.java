package base_knowledge.rpc.server;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DefaultServiceRegister implements ServiceRegister {

    private Map<String, ServiceObject> serviceMap = new HashMap<>();

    @Override
    public void register(ServiceObject serviceObject, String protocol, int port) {
        if (serviceObject == null) {
            throw new IllegalArgumentException("服务对象不能为空！");
        }
        serviceMap.put(serviceObject.getName(), serviceObject);
    }

    @Override
    public ServiceObject getServiceObject(String name) {
        return serviceMap.get(name);
    }
}
