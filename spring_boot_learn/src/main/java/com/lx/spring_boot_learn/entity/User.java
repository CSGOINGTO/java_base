package com.lx.spring_boot_learn.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private String userId;

    private String userName;

    private String password;

    private Timestamp createTime;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp modifyTime;

    public User() {
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
