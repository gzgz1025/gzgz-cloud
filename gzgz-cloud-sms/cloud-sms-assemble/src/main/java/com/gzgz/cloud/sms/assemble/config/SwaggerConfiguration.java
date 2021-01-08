package com.gzgz.cloud.sms.assemble.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ClassName: SwaggerConfiguration
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/3 17:44
 * @Version: 1.0
 */
@Configuration
@EnableKnife4j
public class SwaggerConfiguration {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("GZGZ-CLOUD API文档")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfServiceUrl("http://8.131.76.129:8010")
                        .contact(new Contact("gzgz","xx@qq.com","gzgz1025@aliyun.com"))
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("1.0.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zh.nb.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
