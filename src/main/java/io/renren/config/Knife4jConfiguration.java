package io.renren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("人人基础框架")
                        .description("人人基础框架 RESTful APIs")
                        .termsOfServiceUrl("http://madianpeng.top/")
                        .version("1.0")
                        .contact(new Contact("马典朋", "madianpeng.top", "madianpeng@qq.com"))
                        .build())
                //分组名称
                .groupName("基础服务")
                .select()
                //这里指定Controller扫描包路径
                //.apis(RequestHandlerSelectors.basePackage("io.renren.modules.app.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
