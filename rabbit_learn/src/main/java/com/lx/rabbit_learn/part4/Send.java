package com.lx.rabbit_learn.part4;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.DIRECT_EXCHANGE_NAME;

/**
 * 路由器模式
 * direct exchange
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionsMessage.HOST);
        connectionFactory.setUsername(ConnectionsMessage.USER);
        connectionFactory.setPassword(ConnectionsMessage.PASSWORD);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, "direct");
            String message = null;
            Scanner scanner = new Scanner(System.in);
            while (!"bebe".equals(message)) {
                message = scanner.nextLine();
                if (message.contains("d")) {
                    channel.basicPublish(DIRECT_EXCHANGE_NAME, "delete", null, message.getBytes(StandardCharsets.UTF_8));
                }
                if (message.contains("u")) {
                    channel.basicPublish(DIRECT_EXCHANGE_NAME, "update", null, message.getBytes(StandardCharsets.UTF_8));
                }
                System.out.println("[x] Send '" + message + "'");
            }
        }

    }
}
