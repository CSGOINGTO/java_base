package com.lx.spring_boot_learn;

import com.lx.spring_boot_learn.entity.User;
import com.lx.spring_boot_learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootLearnApplicationTests {

    @Autowired
    private UserService userService;

    private User oldUser = new User(String.valueOf(new Random().nextInt()), "old", "old");

    private User newUser = new User(String.valueOf(new Random().nextInt()), "new", "new");

    @Test
    public void testUser() throws InterruptedException {
        long l = System.currentTimeMillis();
        final int i = 11800000;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        insertUserBatchThread(l, i + 1, 10, countDownLatch);
        insertUserBatchThread(l, i + 2, 10, countDownLatch);
        insertUserBatchThread(l, i + 3, 10, countDownLatch);
        insertUserBatchThread(l, i + 4, 10, countDownLatch);
        insertUserBatchThread(l, i + 5, 10, countDownLatch);
        insertUserBatchThread(l, i + 6, 10, countDownLatch);
        insertUserBatchThread(l, i + 7, 10, countDownLatch);
        insertUserBatchThread(l, i + 8, 10, countDownLatch);
        insertUserBatchThread(l, i + 9, 10, countDownLatch);
        insertUserBatchThread(l, i + 10, 10, countDownLatch);


//        User user = new User();
//        for (int j = 1; j < 10000000; j++) {
//            user.setUserId(String.valueOf(i++));
//            user.setUserName("username" + "_" + i);
//            user.setPassword("password" + "_" + i);
//            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            user.setModifyTime(new Timestamp(System.currentTimeMillis()));
//            userService.insertUserRequired(user);
//        }
        countDownLatch.await();
        System.out.println("总耗时：" + (System.currentTimeMillis() - l));
    }

    private void insertUserThread(long l, int i, int incr, CountDownLatch countDownLatch) {
        new Thread(() -> {
            User user = new User();
            int index5 = i;
            for (int j = 1; j < 50000; j++) {
                user.setUserId(String.valueOf(index5 += incr));
                user.setUserName("username" + "_" + index5);
                user.setPassword("password" + "_" + index5);
                user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                user.setModifyTime(new Timestamp(System.currentTimeMillis()));
                userService.insertUserRequired(user);
            }
            countDownLatch.countDown();
            System.out.println("总耗时：" + (System.currentTimeMillis() - l));
        }).start();
    }

    private void insertUserBatchThread(long l, int i, int incr, CountDownLatch countDownLatch) {
        new Thread(() -> {
            int index5 = i;
            List<User> userList = new ArrayList<>(100000);
            for (int k = 0; k < 10; k++) {
                for (int j = 1; j < 100000; j++) {
                    User user = new User();
                    user.setUserId(String.valueOf(index5 += incr));
                    user.setUserName("username" + "_" + index5);
                    user.setPassword("password" + "_" + index5);
                    user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    user.setModifyTime(new Timestamp(System.currentTimeMillis()));
                    userList.add(user);
                }
                userService.insertUserBatch(userList);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userList.clear();
            }
            countDownLatch.countDown();
//            System.out.println("总耗时：" + (System.currentTimeMillis() - l));
        }).start();
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
