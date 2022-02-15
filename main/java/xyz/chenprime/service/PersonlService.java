package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Perf;
import xyz.chenprime.mapper.PersonalMapper;
import xyz.chenprime.pojo.Performance;
import xyz.chenprime.pojo.Personal;

import java.util.List;

@Service
public class PersonlService {

    @Autowired
    PersonalMapper mapper;

    public Personal getPersonal(Long uid){
        return mapper.getPersonl(uid);
    }

    public List<Performance> getAllPerformanceByUid(Long uid){
        return mapper.getAllPerformanceByUid(uid);
    }

    public boolean addPFM(Performance performance){
        return mapper.addPFM(performance)!=0;
    }

    public boolean updatUserHeadImg(String newfilename,Long uid){
        return mapper.updateUserHeadImg(newfilename,uid)!=0;
    }

    public Personal getPersonalByName(String username){
        return mapper.getPersonalByName(username);
    }

}
