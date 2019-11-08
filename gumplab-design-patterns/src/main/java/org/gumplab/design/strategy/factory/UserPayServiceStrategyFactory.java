package org.gumplab.design.strategy.factory;

import org.gumplab.design.strategy.service.UserPayService;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserPayServiceStrategyFactory {

    private static Map<String, UserPayService> userPayServices = new ConcurrentHashMap<>();

    public static UserPayService getByUserType(String type) {
        return userPayServices.get(type);
    }

    public static void register(String userType, UserPayService userPayService) {
        Assert.notNull(userType, " --> userType can't be null ! ");
        userPayServices.put(userType, userPayService);
    }

}
