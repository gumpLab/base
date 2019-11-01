package org.gumplab.response.common.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String pathMapping;

    @Value("${server.port}")
    private String port;

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API")
                .append("<br/>")
                .append("Response project for Spring Boot");

        return sb.toString();
    }


    @Bean
    public Docket restfulApi() {
        System.out.println("http://localhost:" + port + pathMapping + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Response")
                .apiInfo(initApiInfo())
                //.pathMapping(pathMapping) // base，最终调用接口后会和paths拼接在一起
                .groupName("RestfulApi")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.gumplab.response")) //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //加了ApiOperation注解的类，才生成接口文档
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo initApiInfo() {
        return new ApiInfoBuilder()
                .title("Response")
                .description("0.0.1-SNAPSHOT")
                .version("Response project for Spring Boot")
                .contact("gumpLab")
                .termsOfServiceUrl("") //服务条款
                .license("The Apache License, Version 2.0")//链接显示文字
                .licenseUrl("http://www.baidu.com")//网站链接
                .build();
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/hello.*"),
                regex("/vehicles.*")
        );
    }
}