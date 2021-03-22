package com.lx.spring_boot_learn.mapper;

import com.lx.spring_boot_learn.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insertUser(User user);

    User selectUserByUserId(String id);

    int updateUserByUserId(User user);

    void insertUserBatch(List<User> userList);
}
