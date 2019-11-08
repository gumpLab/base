package org.gumplab.design.strategy.controller;

import org.gumplab.design.ApplicationContextUtil;
import org.gumplab.design.strategy.factory.UserPayServiceStrategyFactory;
import org.gumplab.design.strategy.service.UserPayService;
import org.gumplab.design.strategy.service.impl.VipUserPayServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

// if-else 代码
@RestController
public class UserPayController {

    // 平时 if - else 嵌套大量业务代码，代码很乱
    @GetMapping("/quote")
    public BigDecimal quote(BigDecimal orderPrice, String vipType) {
        if (vipType.equals("VIP")){
            // 业务代码 。。。。。
            return orderPrice.divide(BigDecimal.TEN);
        }
        if (vipType.equals("S-VIP")){
            // 业务代码 。。。。。
            return orderPrice.divide(BigDecimal.TEN).divide(BigDecimal.TEN);
        }
        return orderPrice;
    }

    // 策略模式调整后 ，业务代码分开处理
    @GetMapping("/quote_1")
    public BigDecimal quote1(BigDecimal orderPrice, String vipType) {
        if (vipType.equals("VIP")){
            UserPayService userPayService = ApplicationContextUtil.getBean(VipUserPayServiceImpl.class);
            return userPayService.quote(orderPrice);
        }
        if (vipType.equals("S-VIP")){
            UserPayService userPayService = (UserPayService) ApplicationContextUtil.getBean("superVipUserPayServiceImpl");
            return userPayService.quote(orderPrice);
        }
        return orderPrice;
    }

    // 策略模式加工厂模式改进得到最简优化 ， 工厂调度 bean 处理业务
    @GetMapping("/quote_2")
    public BigDecimal quote2(BigDecimal orderPrice, String vipType) {
        UserPayService userPayService = UserPayServiceStrategyFactory.getByUserType(vipType);
        return userPayService == null ? orderPrice : userPayService.quote(orderPrice);
    }

}
