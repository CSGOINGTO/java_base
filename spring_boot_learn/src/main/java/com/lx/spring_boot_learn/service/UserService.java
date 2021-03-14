package com.lx.spring_boot_learn.service;

import com.lx.spring_boot_learn.entity.User;

public interface UserService {
    boolean insertUserRequired(User user);

    boolean insertUserNewExceptionRequired(User user);

    boolean insertUserNewRequired(User user);

    void insertUserRequiresNew(User user);

    void insertUserExceptionRequiresNew(User user);
}
