package com.lx.rabbit_learn.part5;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.TOPIC_EXCHANGE_NAME;

/**
 * Topics (通配符模式)
 * exchange: topic
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionsMessage.HOST);
        connectionFactory.setUsername(ConnectionsMessage.USER);
        connectionFactory.setPassword(ConnectionsMessage.PASSWORD);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
            String message = null;
            Scanner scanner = new Scanner(System.in);
            while (!"byebye".equals(message)) {
                message = scanner.nextLine();
                // exchange_name, routing key, 类型， 消息
                channel.basicPublish(TOPIC_EXCHANGE_NAME, "delete.xxx", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("[x] Send '" + message + "'");
            }
        }
    }
}
