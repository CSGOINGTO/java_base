package com.lx.kafka_learn.beans;

import java.util.Arrays;

/**
 * 带内AI告警
 */
public class VirtualAiAlarm {
    private String alarm_id;

    private String alarm_content;

    private String alarm_type;

    private long alarm_time;

    private String alarm_title;

    private String alarm_level;

    private String resource_id;

    private String resource_name;

    private String resource_type;

    private String region_id;

    private String datacenter_id;

    private String cluster_id;

    private String host_id;

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

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getDatacenter_id() {
        return datacenter_id;
    }

    public void setDatacenter_id(String datacenter_id) {
        this.datacenter_id = datacenter_id;
    }

    public String getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(String cluster_id) {
        this.cluster_id = cluster_id;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String[] getOriginal_alarms() {
        return original_alarms;
    }

    public void setOriginal_alarms(String[] original_alarms) {
        this.original_alarms = original_alarms;
    }

    @Override
    public String toString() {
        return "VirtualAiAlarm{" +
                "alarm_id='" + alarm_id + '\'' +
                ", alarm_content='" + alarm_content + '\'' +
                ", alarm_type='" + alarm_type + '\'' +
                ", alarm_time=" + alarm_time +
                ", alarm_title='" + alarm_title + '\'' +
                ", alarm_level='" + alarm_level + '\'' +
                ", resource_id='" + resource_id + '\'' +
                ", resource_name='" + resource_name + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", region_id='" + region_id + '\'' +
                ", datacenter_id='" + datacenter_id + '\'' +
                ", cluster_id='" + cluster_id + '\'' +
                ", host_id='" + host_id + '\'' +
                ", original_alarms=" + Arrays.toString(original_alarms) +
                '}';
    }
}
