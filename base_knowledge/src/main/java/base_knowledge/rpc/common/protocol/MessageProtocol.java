package base_knowledge.rpc.common.protocol;

/**
 * 消息协议
 */
public interface MessageProtocol {

    /**
     * 编码请求
     */
    byte[] marshallingRequest(Request request) throws Exception;

    /**
     * 解码请求
     */
    Request unmarshallingRequest(byte[] data) throws Exception;

    /**
     * 编码响应
     */
    byte[] marshallingResponse(Response response) throws Exception;

    /**
     * 解码响应
     */
    Response unmarshallingResponse(byte[] data) throws Exception;

}
