package base_knowledge.rpc.discovery;

import lombok.Data;

/**
 * 服务信息
 */
@Data
public class ServiceInfo {

    /**
     * 服务名
     */
    private String name;

    /**
     * 服务协议
     */
    private String protocol;

    /**
     * 服务地址
     */
    private String address;
}
