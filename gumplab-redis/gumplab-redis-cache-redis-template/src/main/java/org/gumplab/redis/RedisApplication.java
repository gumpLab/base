package org.gumplab.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EntityScan(basePackages = {"org.gumplab.common.entity"})
@EnableJpaRepositories(basePackages = {"org.gumplab.common.service"})
@ComponentScan(basePackages = {"org.gumplab.redis", "org.gumplab.common"})
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
