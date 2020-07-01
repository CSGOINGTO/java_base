package base_knowledge.rpc.common.protocol.impl;

import base_knowledge.rpc.common.protocol.MessageProtocol;
import base_knowledge.rpc.common.protocol.Request;
import base_knowledge.rpc.common.protocol.Response;
import com.google.gson.Gson;

public class JsonMessageProtocol implements MessageProtocol {

    private final Gson gson = new Gson();

    @Override
    public byte[] marshallingRequest(Request request) throws Exception {
        Request temp = new Request();
        temp.setServiceName(request.getServiceName());
        temp.setMethod(request.getMethod());
        temp.setParameterTypes(request.getParameterTypes());
        temp.setHeaders(request.getHeaders());
        final Object[] parameters = request.getParameters();
        if (parameters != null) {
            final Object[] serialParams = new Object[parameters.length];
            for (int i = 0; i < serialParams.length; i++) {
                serialParams[i] = gson.toJson(serialParams[i]);
            }
            temp.setParameters(parameters);
        }
        return gson.toJson(temp).getBytes();
    }

    @Override
    public Request unmarshallingRequest(byte[] data) throws Exception {

        return null;
    }

    @Override
    public byte[] marshallingResponse(Response response) throws Exception {
        return new byte[0];
    }

    @Override
    public Response unmarshallingResponse(byte[] data) throws Exception {
        return null;
    }
}
