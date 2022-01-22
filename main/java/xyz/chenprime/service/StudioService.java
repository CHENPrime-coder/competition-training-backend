package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.StudioMapper;
import xyz.chenprime.pojo.Studio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudioService {

    @Autowired
    StudioMapper mapper;

    public List<Studio> getAllStudio(){
        List<Studio> studios = mapper.getAllStudios();
        for (Studio studio : studios) {
            studio.setSmembers(studio.getSmembers().substring(3)); //把前面占位的'[[,'删掉
        }
        return studios;
    }

    public Map<String,String> updateIcon(String filename, String sname){
        Date date = new Date();
        long time = date.getTime();
        int i = filename.lastIndexOf('.');
        String perfix = filename.substring(0,i);
        String surfix = filename.substring(i);
        filename = perfix+time+surfix;

        Map<String,String> result = new HashMap<>();
        if(mapper.updateIcon(filename,sname)!=0){
            result.put("code", "200");
        }else {
            result.put("code", "406");
        }
        result.put("filename",filename);

        return result;
    }

}
