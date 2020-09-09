package base_knowledge.network.bio;


import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Protocol {

    /**
     * 读取信息
     */
    TeaMessage read(InputStream inputStream) throws IOException;

    /**
     * 端对端发送消息
     *
     * @param header  发送消息目的地
     * @param message 发送消息
     */
    void write(OutputStream outputStream, TeaHeader header, String message) throws IOException;

    /**
     * 转发消息
     * @param teaMessage 转发信息
     */
    void write(OutputStream outputStream, TeaMessage teaMessage) throws IOException;
}
