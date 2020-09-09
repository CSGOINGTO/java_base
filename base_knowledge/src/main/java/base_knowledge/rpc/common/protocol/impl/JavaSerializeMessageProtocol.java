package base_knowledge.rpc.common.protocol.impl;

import base_knowledge.rpc.common.protocol.MessageProtocol;
import base_knowledge.rpc.common.protocol.Request;
import base_knowledge.rpc.common.protocol.Response;

import java.io.*;

public class JavaSerializeMessageProtocol implements MessageProtocol {

    private byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(o);
        return byteArrayOutputStream.toByteArray();
    }

    private Object deSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        System.out.println(new String(bytes));
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    @Override
    public byte[] marshallingRequest(Request request) throws Exception {
        return serialize(request);
    }

    @Override
    public Request unmarshallingRequest(byte[] data) throws IOException, ClassNotFoundException {
        return (Request) deSerialize(data);
    }

    @Override
    public byte[] marshallingResponse(Response response) throws Exception {
        return serialize(response);
    }

    @Override
    public Response unmarshallingResponse(byte[] data) throws IOException, ClassNotFoundException {
        return (Response) deSerialize(data);
    }
}
