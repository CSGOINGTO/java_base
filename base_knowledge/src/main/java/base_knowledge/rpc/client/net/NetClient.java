package base_knowledge.rpc.client.net;

import base_knowledge.rpc.discovery.ServiceInfo;

/**
 * 客户端网络请求
 */
public interface NetClient {

    /**
     * 发送请求
     * @param data 请求数据
     * @param serviceInfo 请求服务信息
     * @return 请求相应
     */
    byte[] sendRequest(byte[] data, ServiceInfo serviceInfo);
}
