package xyz.chenprime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.chenprime.pojo.User;
import xyz.chenprime.service.UserService;
import xyz.chenprime.utils.JwtUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccessController {

    @Autowired
    UserService service;

    @PostMapping("/login")
    public Map<String,String> userLogin(User user, HttpServletResponse response){
        Map<String,String> result = new HashMap<>();
        User chkUser = service.userLogin(user);
        if(chkUser!=null){
            try {
                String token = JwtUtils.createToken(chkUser.getUid(),chkUser.getRole(),chkUser.getUsername());
                result.put("code","200");
                Cookie cookie = new Cookie("token",token);
                cookie.setHttpOnly(true); //防止js更改
                response.addCookie(cookie);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("code","401");
                return result;
            }
        }else {
            result.put("code","400");
        }
        return result;
    }

    @PostMapping("/reg")
    public Map<String,String> userReg(User user,@RequestParam("sname") String sname){
        Map<String,String> result = new HashMap<>();
        if(service.userReg(user,sname)){
            result.put("code","200");
        }else {
            result.put("code","402");
        }
        return result;
    }

}
