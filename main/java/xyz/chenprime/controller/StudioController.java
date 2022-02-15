package xyz.chenprime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.chenprime.pojo.Studio;
import xyz.chenprime.service.StudioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作室无法手动创建，需要导入用户的同时创建。所以不需要创建接口
 */
@RestController
public class StudioController {

    @Autowired
    StudioService service;

    @GetMapping("/studio")
    public List<Studio> getAllStudios(){
        return service.getAllStudio();
    }

    @GetMapping("/studio/{sid}")
    public Studio getStudioBySid(@PathVariable("sid")Long sid){
        return service.getStudioBySid(sid);
    }

    @PostMapping("/studio")
    public Map<String,String> updateIcon(@RequestParam("filename")String filename,
                                         @RequestParam("sname")String sname){
        return service.updateIcon(filename,sname);
    }

}
