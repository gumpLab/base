package org.gumplab.essays.module.controller;

import io.swagger.annotations.ApiOperation;
import org.gumplab.essays.module.entity.User;
import org.gumplab.common.enums.ResultCode;
import org.gumplab.common.exception.BusinessException;
import org.gumplab.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/response")
public class ResponseController {

    @Autowired
    private org.gumplab.essays.module.service.BaseService japApiService;

    @ApiOperation("test nullpointexception")
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

    @ApiOperation("Result response test")
    @GetMapping(value = "/getResult")
    public Result getResult() {
        return Result.success(User.builder().build());
    }

    @ApiOperation("String response test")
    @GetMapping(value = "/getStr")
    public String getStr() {
        return "abcdefghiklmn";
    }

    @ApiOperation("error response test")
    @GetMapping(value = "/error1")
    public Integer error1() {
        return Integer.valueOf("a");
    }

    @ApiOperation("BusinessException response test")
    @GetMapping(value = "/error2")
    public String error2() {
        throw new BusinessException(ResultCode.businses_exception);
    }
}
