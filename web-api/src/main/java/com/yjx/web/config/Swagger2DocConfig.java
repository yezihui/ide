package com.yjx.web.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.ContentType;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static cn.hutool.core.collection.CollUtil.newHashSet;
import static java.util.Collections.singletonList;

/**
 * <p>
 * swagger自定义配置
 * </p>
 *
 * @author yejx
 * @date 2019/12/18 16:35
 */
@Configuration
@Profile({"dev","test"})
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2DocConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(singletonList(tokenKey()))
                .securityContexts(singletonList(securityContext()))
                .protocols(CollUtil.newHashSet("HTTP"))
                .produces(CollUtil.newHashSet(ContentType.JSON.toString()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台系统接口")
                .description("提供后台系统所有服务接口")
                .version("1.0.build")
                .license("xxxxxxx")
                .licenseUrl("http://xxxxxxxxxxxxx")
                .contact(new Contact("yejx",
                        "http://xxxxxxxxxxxxx",
                        "xxxxxxxxxxxxx@qq.com"))
                .termsOfServiceUrl("http://xxxxxxxxxxxxx")
                .build();
    }

    /**
     * 配置基于 ApiKey 的鉴权对象
     */
    private ApiKey tokenKey() {
        return new ApiKey("Authorization", "token", ApiKeyVehicle.HEADER.getValue());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^.*$"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope tokenScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] tokenAuthScopes = new AuthorizationScope[1];
        tokenAuthScopes[0] = tokenScope;
        return singletonList(
                new SecurityReference("Authorization", tokenAuthScopes));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}
