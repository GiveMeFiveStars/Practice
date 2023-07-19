package com.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //初始页面跳转
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
    }
    //设置静态扫描路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
//    @Autowired
//
//    @Qualifier(value="loginInterceptor")
//    private HandlerInterceptor handlerInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration=
//                registry.addInterceptor(handlerInterceptor);
//        //拦截请求
//        registration.addPathPatterns("/**");
//        //放行请求
//        registration.excludePathPatterns(
//                "/login",
//                "/api/**",
//                "/css/**",
//                "/echarts/**",
//                "/images/**",
//                "/js/**",
//                "/lib/**",
//                "/webjars/**",
//                "/captcha",
//                "/templates/**"
//        );
//    }
}
