package xyz.chenprime.interceptor;

import org.apache.catalina.Session;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.chenprime.utils.JwtUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前，进行token验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            session.setAttribute("code","401");
            return false;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                if(JwtUtils.verifyToken(cookie.getValue())!=null){
                    session.setAttribute("code","200");
                    return true;
                }else {
                    session.setAttribute("code","401");
                    return false;
                }
            }
        }
        session.setAttribute("code","401");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
