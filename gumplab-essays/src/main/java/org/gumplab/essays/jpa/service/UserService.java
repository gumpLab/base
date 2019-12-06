package org.gumplab.essays.jpa.service;

import org.gumplab.essays.jpa.entity.User;

import java.util.concurrent.Future;

public interface UserService {

    User asyncTest1(Long id);

    Future<User> asyncTest2(Long id);
}
