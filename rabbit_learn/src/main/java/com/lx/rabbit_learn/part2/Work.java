package com.lx.rabbit_learn.part2;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.QUEUE_NAME_PART2;

public class Work {
    private static final ConnectionFactory factory = new ConnectionFactory();
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
        // 队列名，是否持久化
        channel.queueDeclare(QUEUE_NAME_PART2, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 同一时刻生产者之后发送一条消息给消费者(流量控制)
        channel.basicQos(1);

        DeliverCallback deliverCallback = (s, delivery) -> {
            Work2.doSomething(delivery);
            // 手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        // false:手动返回完成状态 true:表示自动
        // 队列名，是否自动确认消息
        channel.basicConsume(QUEUE_NAME_PART2, false, deliverCallback, consumerTag -> {
        });
    }
}
