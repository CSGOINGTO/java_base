package com.lx.rabbit_learn.part2;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.QUEUE_NAME_PART2;

public class Work2 {

    private static ConnectionFactory factory = new ConnectionFactory();
    private static Channel channel;

    static {
        try {
            factory.setHost(ConnectionsMessage.HOST);
            factory.setUsername(ConnectionsMessage.USER);
            factory.setPassword(ConnectionsMessage.PASSWORD);
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        channel.basicQos(1);
        channel.queueDeclare(QUEUE_NAME_PART2, true, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (s, delivery) -> {
            doSomething(delivery);
            // 手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME_PART2, false, deliverCallback, consumerTag -> {
        });
    }

    /**
     * 工作
     */
    static void doSomething(Delivery delivery) {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        int count = 0;
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                count++;
            }
        }
        System.out.println(" [x] Received '" + message + "'" + ".的个数为: " + count);
        try {
            doWork(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(" [x] Done");
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(10);
            }
        }
    }
}
