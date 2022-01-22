package xyz.chenprime.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.chenprime.pojo.AppFile;
import xyz.chenprime.service.FileService;
import xyz.chenprime.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class FileController {

    @Autowired
    FileService service;

    @PostMapping("/file")
    public Map<String,String> uploadFile(@RequestPart("filebody")MultipartFile filebody,
                                         @RequestParam("filename")String filename){
        Map<String,String> result = new HashMap<>();

        AppFile file = new AppFile();
        file.setOldname(filebody.getOriginalFilename());
        file.setName(filename);
        file.setUrl("D:\\springBootUpload\\"+filename);
        file.setType(filebody.getContentType());
        if(FileUtils.upload(filebody,filename).equals("200")){//上传成功
            if(service.addFile(file)){
                result.put("code","200");
            }else {
                result.put("code","408");
            }
        }else {
            result.put("code","408");
        }
        return result;
    }
}
