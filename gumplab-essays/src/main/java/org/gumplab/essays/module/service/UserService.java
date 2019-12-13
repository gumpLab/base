package org.gumplab.essays.module.service;

import org.gumplab.essays.module.entity.User;

import java.util.concurrent.Future;

public interface UserService {

    void asyncTest1(User user);

    Future<User> asyncTest2(User user);
}
