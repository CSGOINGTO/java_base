package com.lx.rabbit_learn.part1;

import com.lx.rabbit_learn.constants.ConnectionsMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.lx.rabbit_learn.constants.QueueExchangeName.QUEUE_NAME_PART1;

/**
 * 通过默认的Exchange发送到指定的Queue。
 * 默认的Exchange隐式的绑定到每个队列，并且routing key和queue name一样。
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ConnectionsMessage.HOST);
        factory.setUsername(ConnectionsMessage.USER);
        factory.setPassword(ConnectionsMessage.PASSWORD);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
             Scanner in = new Scanner(System.in)) {
            // 队列声明
            channel.queueDeclare(QUEUE_NAME_PART1, false, false, false, null);
            String message = in.next();
            while (!"再见".equals(message)) {
                // 发送到默认的exchange， queue的名字就是queue_name，消息类型，消息
                channel.basicPublish("", QUEUE_NAME_PART1, null, message.getBytes());
                message = in.next();
            }
            System.out.println(" [x] Send '" + message + "'");
        }

    }
}
