package com.lx.rabbit_learn.part3;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.FANOUT_EXCHANGE_NAME;

public class Receive1 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionsMessage.HOST);
        connectionFactory.setUsername(ConnectionsMessage.USER);
        connectionFactory.setPassword(ConnectionsMessage.PASSWORD);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout");
        // 获取默认绑定的queue
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("绑定的queue----------------------->" + queueName);
        // queueName,exchangeName,routingKey
        channel.queueBind(queueName, FANOUT_EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
