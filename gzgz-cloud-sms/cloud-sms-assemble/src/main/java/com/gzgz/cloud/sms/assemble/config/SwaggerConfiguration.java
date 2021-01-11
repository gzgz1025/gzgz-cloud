package com.gzgz.cloud.sms.assemble.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfiguration
 * @Description:
 * @Author: pzl
 * @CreateDate: 2020/12/3 17:44
 * @Version: 1.0
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value="swagger.enabled")
public class SwaggerConfiguration {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("GZGZ-CLOUD-SMS API文档")
                        .description("# 短信统一接口平台 APIs")
                        .termsOfServiceUrl("http://127.0.0.1:8011")
                        .contact(new Contact("gzgz","xx@qq.com","gzgz1025@aliyun.com"))
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("1.0.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gzgz.cloud.sms.web"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
