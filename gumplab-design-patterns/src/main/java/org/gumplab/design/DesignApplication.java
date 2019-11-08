package org.gumplab.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DesignApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DesignApplication.class, args);
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }

}
