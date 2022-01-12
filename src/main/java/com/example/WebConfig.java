package com.example;

import com.example.interceptor.LogInterceptor;
import com.example.interceptor.LoginCheckInterceptor;
import com.example.interceptor.PermissionInterceptor;
import com.example.interceptor.UserAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/*.ico","/error","/*.css","/*.js");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/logout","/css/**","/*.ico","/error","/login/loginForm","/noAuth","/vendor/**","/scss/**","/img/**","/js/**");

//        registry.addInterceptor(new UserAuthInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login","/logout","/css/**","/*.ico","/error","/login/loginForm","","/noAuth","/vendor/**","/scss/**","/img/**","/js/**");

        registry.addInterceptor(new PermissionInterceptor())
                .order(3)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/logout","/css/**","/*.ico","/error","/login/loginForm","","/noAuth","/member/**","/vendor/**","/scss/**","/img/**","/js/**");

    }
}
