package base_knowledge.rpc.common.protocol;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息协议相应
 */
@Data
public class Response implements Serializable {

    public static final long serialVersionUID = 123123123L;

    /**
     * 消息头
     */
    private Map<String, String> headers;

    /**
     * 消息状态
     */
    private Status status;

    /**
     * 返回类型
     */
    private Class<?>[] returnType;

    /**
     * 返回值
     */
    private Object returnValue;

    /**
     * 处理中发生的异常
     */
    private Exception exception;

    public Response(Status status) {
        this.status = status;
    }

    /**
     * 获取头信息
     */
    public String getHeader(String name) {
        return headers.get(name);
    }

    /**
     * 添加头信息
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
}
