package com.lx.kafka_learn.test;

import com.google.gson.Gson;
import com.lx.kafka_learn.beans.DeviceAiAlarm;
import com.lx.kafka_learn.beans.VirtualAiAlarm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTest {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Test
    public void testVirtualAiAlarm() throws InterruptedException {

        String str = "{\n" +
                "\"alarm_id\": \"02fa5c7684dd4a409657aef9bccc11b1\",\n" +
                "\"alarm_content\": \"虚拟机故障\",\n" +
                "\"alarm_type\": \"convergence\",\n" +
                "\"alarm_time\": 15655878900,\n" +
                "\"alarm_title\": \"cpu 使用率严重告警\",\n" +
                "\"alarm_level\": \"1\",\n" +
                "\"resource_id\": \"98dfc90bb4fa11e9bf29005056a5c081\",\n" +
                "\"resource_name\": \"VM-1111\",\n" +
                "\"resource_type\": \"vm\",\n" +
                "\"region_id\": \"2988b7a18f3c41ba94106ac6283663d8\",\n" +
                "\"datacenter_id\": \"ba94106ac6283663d8\",\n" +
                "\"cluster_id\": \"2988b7a18f3c41ba\",\n" +
                "\"host_id\": \"sdfje18f3c41ba\",\n" +
                "\"original_alarms\": [\n" +
                "\"47607e76553d4faeb0d51224a4e36471\"\n" +
                "]\n" +
                "}";
        Gson gson = new Gson();
        VirtualAiAlarm virtualAiAlarm = null;
        for (int i = 0; i < 100000; i++) {
            virtualAiAlarm = gson.fromJson(str, VirtualAiAlarm.class);
            virtualAiAlarm.setAlarm_id(UUID.randomUUID().toString());
            virtualAiAlarm.setAlarm_content("我是第" + i + "个告警！！！");
            kafkaTemplate.send("virtual_ai_alarm", gson.toJson(virtualAiAlarm));
        }
        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
//        Thread.sleep(5000);
    }

    @Test
    public void testDeviceAiAlarm() throws InterruptedException {

        String str = "{\"alarm_id\": \"7d705ec195774caabfdde6d536b6b1aa\"," +
                "\"alarm_content\": \"物理机故障\", \n" +
                "\"alarm_type\": \"correlation\",\"alarm_time\": 1565587890,\"alarm_title\": \"cpu 使用率严重告警\", \n" +
                "\"alarm_level\": \"1\",\"device_id\": \"ldaj2900d5056a5c081\",\"device_name\": \"1e9bjlasdoo005056a5c081\"," +
                "\"device_sn\": \"1e9bf29005056a5c081\",\"device_type\": \"switch\",\"park_id\": \"298a94106ac6283663d8\", " + " \"room_id\": \"ba94106ac6283663d8\"," +
                "\"chassis_id\": \"2988b7a18f3c41ba\",\"original_alarms\": [\"47607e76553d4faeb0d51224a4e36471\",\"6762s378p56d4f1eb7d51329as857017\"]}";
        Gson gson = new Gson();
        DeviceAiAlarm deviceAiAlarm = null;
        for (int i = 0; i < 10; i++) {
            deviceAiAlarm = gson.fromJson(str, DeviceAiAlarm.class);
            deviceAiAlarm.setAlarm_id(UUID.randomUUID().toString());
            deviceAiAlarm.setAlarm_content("我是第" + i + "个告警！！！");
            kafkaTemplate.send("device_ai_alarm", gson.toJson(deviceAiAlarm));
        }
        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
//        Thread.sleep(5000);
    }
}
