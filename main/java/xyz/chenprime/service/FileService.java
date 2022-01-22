package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.FileMapper;
import xyz.chenprime.pojo.AppFile;

@Service
public class FileService {
    @Autowired
    FileMapper mapper;
    //保存文件信息到sql中
    public boolean addFile(AppFile file){
        return mapper.addFile(file)!=0;
    }
}
