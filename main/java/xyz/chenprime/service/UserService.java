package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.UserMapper;
import xyz.chenprime.pojo.User;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper mapper;

    public User userLogin(User user){
        return mapper.isExists(user);
    }

    /**
     * 用户注册
     * @param user 用户对象
     * @param sname 工作室名字
     */
    public boolean userReg(User user,String sname){
        if(mapper.getUserById(user.getUid())!=null){
            return false;
        }
        if(!isExistsByName(sname)){
            createStudio(sname);
        }
        //添加用户
        int res1 = mapper.insertUser(user);
        //添加成员
        int res2 = mapper.addMember(","+user.getUsername(),sname);
        //在关联表中添加uid对应sid
        int res3 = mapper.addRelation(mapper.getSidBySname(sname),mapper.isExists(user).getUid());
        return res1 != 0 && res2 != 0 && res3 != 0;
    }

    //判断工作室是否存在
    private boolean isExistsByName(String sname){
        return mapper.isExistsByName(sname) != null;
    }

    //创建工作室
    private void createStudio(String sname){
        mapper.createStudio(sname);
    }

}
