package org.gumplab.essays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.gumplab.essays", "org.gumplab.common"})
@SpringBootApplication
public class EssaysApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssaysApplication.class, args);
    }

}
