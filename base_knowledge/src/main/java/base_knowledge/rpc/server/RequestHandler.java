package base_knowledge.rpc.server;

import base_knowledge.rpc.common.protocol.MessageProtocol;
import base_knowledge.rpc.common.protocol.Request;
import base_knowledge.rpc.common.protocol.Response;
import base_knowledge.rpc.common.protocol.Status;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 请求处理器
 * @author liuxue
 */
@Data
public class RequestHandler {
    private MessageProtocol protocol;

    private ServiceRegister serviceRegister;

    public RequestHandler(MessageProtocol protocol, ServiceRegister serviceRegister) {
        this.protocol = protocol;
        this.serviceRegister = serviceRegister;
    }

    public byte[] handleRequest(byte[] bytes) throws Exception {
        final Request request = protocol.unmarshallingRequest(bytes);
        final ServiceObject serviceObject = serviceRegister.getServiceObject(request.getServiceName());
        Response response = null;
        if (serviceObject == null) {
            response = new Response(Status.NOT_FOUND);
        } else {
            try {
                final Method method = serviceObject.getInterf().getMethod(request.getMethod(), request.getParameterTypes());
                final Object returnValue = method.invoke(serviceObject.getObj(), request.getParameters());
                response = new Response(Status.SUCCESS);
                response.setReturnValue(returnValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
                response = new Response(Status.ERROR);
                response.setException(e);
            }
        }
        return protocol.marshallingResponse(response);
    }
}
