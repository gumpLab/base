package org.gumplab.response.jpa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.gumplab.response.common.result.Result;
import org.gumplab.response.jpa.entity.User;
import org.gumplab.response.jpa.service.JapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "API - JpaApiController", description = "This is spring-data-jpa base api !")
@RestController
@RequestMapping(value = "/api-jpa")
public class JpaApiController {

    @Autowired
    private JapApiService japApiService;

    @ApiOperation("select all users")
    @GetMapping("/findAll")
    public void findAll() {
        List<User> all = japApiService.findAll();
        System.out.println(all.get(0).getName());
    }

    @ApiOperation("empty response test")
    @GetMapping(value = "/empty")
    public void empty() {
    }

    @ApiOperation("error response test")
    @GetMapping(value = "/error")
    public void error() {
        int i = 9 / 0;
    }

    @ApiOperation("success response test")
    @GetMapping(value = "/getResult")
    public Result getResult() {
        return Result.success(new User());
    }

    @ApiOperation("success response test")
    @GetMapping(value = "/getStr")
    public String getStr() {
        return "afdasfdsdfsf";
    }

    @ApiOperation("error response test")
    @GetMapping(value = "/error1")
    public Integer error1() {
        return Integer.valueOf("a");
    }
}
