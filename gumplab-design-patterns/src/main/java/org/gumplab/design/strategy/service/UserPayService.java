package org.gumplab.design.strategy.service;

import java.math.BigDecimal;

public interface UserPayService {

    // 根据商品价格计算应付金额
    BigDecimal quote(BigDecimal orderPrice);

}
