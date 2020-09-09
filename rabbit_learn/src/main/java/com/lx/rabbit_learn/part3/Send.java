package com.lx.rabbit_learn.part3;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.FANOUT_EXCHANGE_NAME;

/**
 * publish/subscribe(发布/订阅模式)
 * fanout exchange
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionsMessage.HOST);
        connectionFactory.setUsername(ConnectionsMessage.USER);
        connectionFactory.setPassword(ConnectionsMessage.PASSWORD);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();) {
            // 声明exchange的模式为fanout
            channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout");
            String message = "Test fanout exchange";
            // 发送消息 exchangeName,routingKey
            channel.basicPublish(FANOUT_EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Send '" + message + "'");
        }
    }
}
