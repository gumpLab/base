package org.gumplab.redis.module.demo1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gumplab.common.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping
public class RedisTemplateController {

    private RedisTemplate redisTemplate;

    @GetMapping
    public void redisTemplate() {
        redisTemplate.opsForValue().set("userKey", User.builder().id(10L).build());
        User user = (User) redisTemplate.opsForValue().get("userKey");
        log.info("redis cache template test: {}", user);
    }
}
