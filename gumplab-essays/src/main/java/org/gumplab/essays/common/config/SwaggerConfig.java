package org.gumplab.essays.common.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Value("${server.servlet.context-path}")
    private String pathMapping;

    @Value("${server.port}")
    private String port;

    @Bean
    public Docket createRestApi() {
        log.info("http://127.0.0.1:" + port + pathMapping + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.gumplab.essays.module")) //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //加了ApiOperation注解的类，才生成接口文档
                .paths(PathSelectors.any())
                .build()
                //.globalOperationParameters(parameters())
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("essays")
                .description("essays every day ~")
                .version("0.0.1")
                .build();
    }

    private List<ApiKey> security() {
        return newArrayList (
                new ApiKey("token", "token", "header")
        );
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