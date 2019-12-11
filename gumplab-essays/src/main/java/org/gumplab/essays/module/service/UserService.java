package org.gumplab.essays.module.service;

import org.gumplab.essays.module.entity.User;

import java.util.concurrent.Future;

public interface UserService {

    User asyncTest1(Long id);

    Future<User> asyncTest2(Long id);
}
