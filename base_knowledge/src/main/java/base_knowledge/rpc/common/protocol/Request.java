package base_knowledge.rpc.common.protocol;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息协议请求
 */
@Data
public class Request implements Serializable {

    private static final long serialVersionUID = 12312312L;

    /**
     * 消息头
     */
    private Map<String, String> headers;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务方法
     */
    private String method;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数
     */
    private Object[] parameters;

    /**
     * 获取消息头信息
     */
    public String getHeaders(Status name) {
        return headers.get(name);
    }
}
