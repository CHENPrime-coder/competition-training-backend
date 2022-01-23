package xyz.chenprime.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类，包含上传，下载
 */
@Component
public class FileUtils {
    /**
     * 文件上传
     * @param file 文件MultipartFile对象
     * @param filename 保存到服务器的名字
     * @return code值
     */
    public static String upload(MultipartFile file,String filename){
        if(!file.isEmpty()){    //文件不是空的
            try{
                file.transferTo(new File("/springbootUPLOAD/"+filename));
            }catch (IOException e){
                return "408";
            }
        }
        return "200";
    }
}
