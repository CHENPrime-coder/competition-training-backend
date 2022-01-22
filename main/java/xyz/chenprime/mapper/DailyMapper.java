package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.chenprime.pojo.Daily;

import java.util.List;

@Mapper
public interface DailyMapper {

    @Select("select * from daily where ddate like concat(#{now},'%') or ddate like concat(#{yesterday},'%')")
    List<Daily> getAllDaily(String now,String yesterday);

    @Select("select * from daily where reporter=#{username}")
    List<Daily> getDaiilyByUserName(String username);

        @Insert("insert into daily(dname, reporter, dbody) values " +
            "(#{dname},#{reporter},#{dbody})")
    int reportDaily(Daily daily);

}
