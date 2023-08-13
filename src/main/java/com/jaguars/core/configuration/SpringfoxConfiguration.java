package com.jaguars.core.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jaguars.core.enumerate.APIMProductEnum;

// import com.jaguars.core.enumerate.APIMProductEnum;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
public class SpringfoxConfiguration implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton("bean-default", findDefaultApi());
    }

    private Docket findDefaultApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo("jaguars-backend")).select()
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getApiInfo(String description) {
        return getApiInfo(description, "1.0");
    }

    private ApiInfo getApiInfo(String description, String version) {
        var builder = new ApiInfoBuilder();
        builder.title("jaguars");
        builder.description(description);
        builder.version(version);
        return builder.build();
    }
}
