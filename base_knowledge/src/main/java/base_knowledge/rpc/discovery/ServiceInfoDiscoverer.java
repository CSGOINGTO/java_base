package base_knowledge.rpc.discovery;

import java.util.List;

/**
 * 服务发现
 */
public interface ServiceInfoDiscoverer {

    /**
     * 获取服务信息
     */
    List<ServiceInfo> getServiceInfo(String name);
}
