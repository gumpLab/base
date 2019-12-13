package org.gumplab.essays.module.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gumplab.essays.module.entity.User;
import org.gumplab.essays.module.service.UserService;
import org.gumplab.response.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Future;

@Api
@Slf4j
@RestController
@RequestMapping(value = "/async")
public class AsyncLearnController {

    @Autowired
    private UserService userService;

    @ApiOperation("TestAsync")
    @GetMapping
    public List<User> findAll() {

        userService.asyncTest1(User.builder().id(1L).build());
        log.info("Async Test-1 - Controller: [{}]", Thread.currentThread().getName());

        try {
            Future<User> user2Future = userService.asyncTest2(User.builder().id(2L).build());
            log.info("Async Test-2 - Controller: [{}], [{}]", user2Future.get(), Thread.currentThread().getName());
        } catch (Exception e) {
            throw new BusinessException(110, "异步调用异常！");
        }

        return Lists.newArrayList();

    }
}
