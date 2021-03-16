package com.lx.spring_boot_learn.service.impl;

import com.lx.spring_boot_learn.entity.User;
import com.lx.spring_boot_learn.mapper.UserMapper;
import com.lx.spring_boot_learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insertUserRequired(User user) {
        if (userMapper.insertUser(user) > 0) {
//            return true;
        }
//        User user1 = new User();
//        user1.setUserName("new");
//        user1.setUserId(String.valueOf(new Random().nextInt()));
//        user1.setPassword("new");
//        insertUserNew(user1);
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insertUserNewExceptionRequired(User user) {
//        try {
            userMapper.insertUser(user);
            throw new RuntimeException("insertUserNew");
//        } catch (Exception e) {
//            System.out.println("insertUserNewException...........................................");
//        }
//        return false;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean insertUserNewRequired(User user) {
        userMapper.insertUser(user);
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertUserRequiresNew(User user) {
        userMapper.insertUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertUserExceptionRequiresNew(User user) {
        userMapper.insertUser(user);
        throw new RuntimeException();
    }


}
