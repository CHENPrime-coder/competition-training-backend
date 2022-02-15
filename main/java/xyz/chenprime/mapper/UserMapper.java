package xyz.chenprime.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.chenprime.pojo.Studio;
import xyz.chenprime.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where username=#{username} and password=#{password}")
    User isExists(User user);

    @Insert("insert into users(username, password, telnumber, role, uid) VALUES (#{username},#{password},#{telnumber},#{role},#{uid})")
    int insertUser(User user);

    @Update("update studios set smembers = concat(smembers,#{username}) where sname=#{sname};")
    int addMember(String username,String sname);

    @Select("select * from users where uid=#{uid}")
    User getUserById(Long uid);

    @Select("select * from studios where sname=#{sname}")
    Studio isExistsByName(String sname);

    @Insert("insert into studios(smembers,sname) VALUES ('[[',#{sname})")
    void createStudio(String sname);

    @Insert("insert into relation(sid, uid) VALUES (#{sid},#{uid})")
    int addRelation(Long sid,Long uid);

    @Select("select sid from studios where sname=#{sname}")
    Long getSidBySname(String sname);

    @Select("select * from users where role=#{role}")
    List<User> getAllUserByRole(String role);

}
