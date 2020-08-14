package com.lx.kafka_learn.listen;

import com.google.gson.Gson;
import com.lx.kafka_learn.beans.VirtualAiAlarm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoListener {

    /**
     * 声明consumerID为demo，监听topicName为topic.quick.demo的Topic
     */
    @KafkaListener(id = "demo", topics = "virtual_ai_alarm")
    public void listen(String msgData) {
        log.info("demo receive : " + msgData);
        Gson gson = new Gson();
        final VirtualAiAlarm virtualAiAlarm = gson.fromJson(msgData, VirtualAiAlarm.class);
        System.out.println(virtualAiAlarm);
    }
}
