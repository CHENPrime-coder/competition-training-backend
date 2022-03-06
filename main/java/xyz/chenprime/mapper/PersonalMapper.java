package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.chenprime.pojo.Performance;
import xyz.chenprime.pojo.Personal;

import java.util.List;

@Mapper
public interface PersonalMapper {

    @Select("SELECT u.username AS username,f.url AS headiconURL,s.sname AS sname,u.`uid` as uid FROM users AS u \n" +
            "INNER JOIN relation AS r ON r.`uid`=u.`uid`\n" +
            "INNER JOIN files AS f ON f.`name`=u.`headicon`\n" +
            "INNER JOIN studios AS s ON r.`sid`=s.`sid`\n" +
            "WHERE u.`uid`=#{uid};")
    Personal getPersonl(Long uid);

    @Select("select * from performance where uid=#{uid}")
    List<Performance> getAllPerformanceByUid(Long uid);

    @Insert("insert into performance(uid, score, info) VALUES " +
            "(#{uid},#{score},#{info})")
    int addPFM(Performance performance);

    @Update("update users set headicon = #{newfilename} where uid=#{uid};")
    int updateUserHeadImg(String newfilename,Long uid);

    @Select("SELECT u.username AS username,f.url AS headiconURL,s.sname AS sname,u.`uid` as uid FROM users AS u \n" +
            "INNER JOIN relation AS r ON r.`uid`=u.`uid`\n" +
            "INNER JOIN files AS f ON f.`name`=u.`headicon`\n" +
            "INNER JOIN studios AS s ON r.`sid`=s.`sid`\n" +
            "WHERE u.`username`=#{username};")
    Personal getPersonalByName(String username);

    @Select("select * from users where username=#{username}")
    String getRole(String username);

}
