package xyz.chenprime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.chenprime.interceptor.UserLoginInterceptor;

/**
 * webMvc配置
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    //拦截所有除登陆外的请求，由拦截器处理那些请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/reg");
    }
}
