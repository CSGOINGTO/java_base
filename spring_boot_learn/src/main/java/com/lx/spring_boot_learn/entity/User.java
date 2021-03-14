package com.lx.spring_boot_learn.entity;

import lombok.Data;

@Data
public class User {
    private String userId;

    private String userName;

    private String password;

    public User() {
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
