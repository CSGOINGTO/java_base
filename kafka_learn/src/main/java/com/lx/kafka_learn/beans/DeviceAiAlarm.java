package com.lx.kafka_learn.beans;

import java.util.Arrays;

/**
 * 带外AI告警
 */
public class DeviceAiAlarm {
    private String alarm_id;

    private String alarm_content;

    private String alarm_type;

    private long alarm_time;

    private String alarm_title;

    private String alarm_level;

    private String device_id;

    private String device_name;

    private String device_sn;

    private String device_type;

    private String park_id;

    private String room_id;

    private String chassis_id;

    private String[] original_alarms;

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlarm_content() {
        return alarm_content;
    }

    public void setAlarm_content(String alarm_content) {
        this.alarm_content = alarm_content;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    public long getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(long alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getAlarm_title() {
        return alarm_title;
    }

    public void setAlarm_title(String alarm_title) {
        this.alarm_title = alarm_title;
    }

    public String getAlarm_level() {
        return alarm_level;
    }

    public void setAlarm_level(String alarm_level) {
        this.alarm_level = alarm_level;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getChassis_id() {
        return chassis_id;
    }

    public void setChassis_id(String chassis_id) {
        this.chassis_id = chassis_id;
    }

    public String[] getOriginal_alarms() {
        return original_alarms;
    }

    public void setOriginal_alarms(String[] original_alarms) {
        this.original_alarms = original_alarms;
    }

    @Override
    public String toString() {
        return "DeviceAiAlarm{" +
                "alarm_id='" + alarm_id + '\'' +
                ", alarm_content='" + alarm_content + '\'' +
                ", alarm_type='" + alarm_type + '\'' +
                ", alarm_time=" + alarm_time +
                ", alarm_title='" + alarm_title + '\'' +
                ", alarm_level='" + alarm_level + '\'' +
                ", device_id='" + device_id + '\'' +
                ", device_name='" + device_name + '\'' +
                ", device_sn='" + device_sn + '\'' +
                ", device_type='" + device_type + '\'' +
                ", park_id='" + park_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", chassis_id='" + chassis_id + '\'' +
                ", original_alarms=" + Arrays.toString(original_alarms) +
                '}';
    }
}
