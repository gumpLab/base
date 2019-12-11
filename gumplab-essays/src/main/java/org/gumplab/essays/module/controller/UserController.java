package org.gumplab.essays.module.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gumplab.essays.module.entity.User;
import org.gumplab.essays.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Api
@Slf4j
@RestController
@RequestMapping(value = "/async")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("TestAsync")
    @GetMapping
    public List<User> findAll() throws ExecutionException, InterruptedException {


        User user1 = userService.asyncTest1(1L);
        log.info("Async Test-1 - Controller: [{}], [{}]", Optional.ofNullable(user1).map(User::getName).orElse(null), Thread.currentThread().getName());

        Future<User> user2Future = userService.asyncTest2(2L);
        log.info("Async Test-2 - Controller: [{}], [{}]", Optional.ofNullable(user2Future.get()).map(User::getName).orElse(null), Thread.currentThread().getName());

        return Lists.newArrayList(user1, user2Future.get());

    }
}
