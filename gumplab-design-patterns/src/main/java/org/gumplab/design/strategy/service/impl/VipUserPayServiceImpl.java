package org.gumplab.design.strategy.service.impl;

import org.gumplab.design.strategy.factory.UserPayServiceStrategyFactory;
import org.gumplab.design.strategy.service.UserPayService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VipUserPayServiceImpl implements UserPayService, InitializingBean {

    public BigDecimal quote(BigDecimal orderPrice) {
        return orderPrice.divide(BigDecimal.TEN);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register("VIP", this);
    }
}
