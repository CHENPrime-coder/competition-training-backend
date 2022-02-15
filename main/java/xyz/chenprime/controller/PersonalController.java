package xyz.chenprime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.chenprime.pojo.Performance;
import xyz.chenprime.pojo.Personal;
import xyz.chenprime.service.PersonlService;
import xyz.chenprime.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonalController {

    @Autowired
    PersonlService service;

    @GetMapping("/personal")
    public Personal getinfo(@CookieValue("token") String token){
        String uid = JwtUtils.getTokenMessage("uid",token);
        Long myUid = Long.parseLong(uid);
        return service.getPersonal(myUid);
    }

    @GetMapping("/personal/{uid}")
    public Personal getinfoByUid(@PathVariable("uid") Long uid){
        return service.getPersonal(uid);
    }

    @GetMapping("/personalname/{username}")
    public Personal getinfoByUserName(@PathVariable("username") String username){
        return service.getPersonalByName(username);
    }

    @PostMapping("/pfm")
    public Map<String,String> uploadPerformance(Performance pfm,
                                                @CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        if(JwtUtils.getTokenMessage("role",token).equals("学生")){
            result.put("code","403");
            return result;
        }
        if(service.addPFM(pfm)){
            result.put("code","200");
        }else {
            result.put("code","405");
        }

        return result;
    }

    @GetMapping("/pfm/{uid}")
    public List<Performance> getAllPerformanceByUid(@PathVariable("uid")Long uid){
        return service.getAllPerformanceByUid(uid);
    }

    @PostMapping("/userimg")
    public Map<String,String> changeUserHeadImg(@RequestParam("filename")String filename,
                                                @CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        Long uid = Long.parseLong(JwtUtils.getTokenMessage("uid",token));
        //修改名字
        Date date = new Date();
        long time = date.getTime();
        int i = filename.lastIndexOf('.');
        String perfix = filename.substring(0,i);
        String surfix = filename.substring(i);
        filename = perfix+time+surfix;
        if(service.updatUserHeadImg(filename,uid)){
            result.put("code","200");
            result.put("filename",filename);
        }else {
            result.put("code","406");
        }
        return result;
    }

}
