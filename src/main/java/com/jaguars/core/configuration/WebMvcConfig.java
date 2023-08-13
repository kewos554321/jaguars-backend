package com.jaguars.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")  // 允许对所有的 URL 进行跨源请求
    //         .allowedOrigins("http://localhost:4200") // 允许来自这个源的跨源请求
    //         .allowedOrigins("https://jaguars-web.netlify.app") // 允许来自这个源的跨源请求
    //         .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // 允许这些 HTTP 方法
    //         .allowedHeaders("*")
    //         .allowCredentials(true); // 允许发送 cookie
    // }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("*")
    //             // .allowedOrigins("http://localhost:4200", "https://jaguars-web.netlify.app")
    //             .allowedMethods("GET", "POST", "PUT", "DELETE");
    // }

}
