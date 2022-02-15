package xyz.chenprime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    /**
     * 所有请求都允许跨域，使用这种配置就不需要
     * 在interceptor中配置header了
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080/")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}
