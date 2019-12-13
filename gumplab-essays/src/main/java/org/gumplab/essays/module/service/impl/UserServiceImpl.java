package org.gumplab.essays.module.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.gumplab.essays.module.entity.User;
import org.gumplab.essays.module.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Async
    @Override
    public void asyncTest1(User user) {
        System.out.println(1 / 0);
        log.info("Async Test-1 - Service: [{}]", Thread.currentThread().getName());
    }

    @Async
    @Override
    public Future<User> asyncTest2(User user) {
        log.info("Async Test-2 - Service: [{}]", Thread.currentThread().getName());
        System.out.println(1 / 0);
        return new AsyncResult<>(User.builder().name("Async-Test-2 Result").build());
    }

}
