package org.gumplab.response.jpa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("查询全部用户")
    @GetMapping("/findAll")
    public List<User> findAll(){
        return japApiService.findAll();
    }
}
