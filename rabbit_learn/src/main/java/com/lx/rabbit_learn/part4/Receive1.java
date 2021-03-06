package com.lx.rabbit_learn.part4;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.DIRECT_EXCHANGE_NAME;

public class Receive1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionsMessage.HOST);
        connectionFactory.setUsername(ConnectionsMessage.USER);
        connectionFactory.setPassword(ConnectionsMessage.PASSWORD);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, DIRECT_EXCHANGE_NAME, "delete");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (s, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
