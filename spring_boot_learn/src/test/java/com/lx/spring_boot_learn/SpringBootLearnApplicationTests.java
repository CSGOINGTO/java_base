package com.lx.spring_boot_learn;

import com.lx.spring_boot_learn.entity.User;
import com.lx.spring_boot_learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@SpringBootTest
class SpringBootLearnApplicationTests {

    @Autowired
    private UserService userService;

    private User oldUser = new User(String.valueOf(new Random().nextInt()), "old", "old");

    private User newUser = new User(String.valueOf(new Random().nextInt()), "new", "new");

    @Test
    public void testUser() {
        User user = new User();
        user.setUserId(String.valueOf(new Random().nextInt()));
        user.setUserName("oldTest");
        user.setPassword("oldTest");
        userService.insertUserRequired(user);
    }

    @Test
    public void testUserNew() {
        User user = new User();
        user.setUserId(String.valueOf(new Random().nextInt()));
        user.setUserName("newTest");
        user.setPassword("newTest");
        userService.insertUserNewExceptionRequired(user);
    }

    @Test
    public void testRequiredException() throws Exception {
        userService.insertUserRequired(oldUser);
        userService.insertUserNewExceptionRequired(newUser);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        userService.insertUserRequired(oldUser);
        userService.insertUserNewRequired(newUser);
//        try {
            userService.insertUserNewExceptionRequired(newUser);
//        } catch (Exception e) {
//            System.out.println("异常！");
//        }
    }

    @Test
    public void testRequiresNew() {
        userService.insertUserRequiresNew(oldUser);
        userService.insertUserNewRequired(newUser);
        userService.insertUserExceptionRequiresNew(newUser);
    }

    @Test
    @Transactional
    public void testRequiresNewT() {
        userService.insertUserRequiresNew(oldUser);
        userService.insertUserNewRequired(newUser);
        userService.insertUserExceptionRequiresNew(newUser);
    }
}
