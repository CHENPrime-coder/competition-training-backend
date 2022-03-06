package xyz.chenprime.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应头过滤器，添加跨域请求的配置 OncePerRequestFilter -> doFilterInternal
 */
@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.addHeader("Access-Control-Allow-Methods","GET,PUT,DELETE,POST,OPTIONS");
        response.addHeader("Access-Control-Allow-Origin","https://1.117.145.48");
        filterChain.doFilter(request,response);
    }
}
