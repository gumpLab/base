package org.gumplab.redis.module.demo2;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gumplab.common.entity.User;
import org.gumplab.common.service.BaseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Service
@AllArgsConstructor
public class RedisCacheService {

    private BaseService baseService;

    @CachePut(value = "user", key = "#user.id")
    public User save(User user) {
        log.info("进入save方法，当前存储对象：{}", user.toString());
        return baseService.save(user);
    }

    @CacheEvict(value = "user", key = "#id")
    public void deleteById(Long id) {
        baseService.deleteById(id);
        log.info("进入delete方法，删除成功");
    }

    @Cacheable(value = "user", key = "#id")
    public User getOne(@PathVariable Long id) {
        log.info("进入get方法，当前获取对象ID：{}", id);
        return baseService.findById(id).orElse(null);
    }

}
