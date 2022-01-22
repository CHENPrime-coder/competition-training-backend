package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.chenprime.pojo.Studio;

import java.util.List;

@Mapper
public interface StudioMapper {

    @Select("select * from studios")
    List<Studio> getAllStudios();

    @Update("update studios set sheadicon=#{filename} where sname=#{sname}")
    int updateIcon(String filename,String sname);

}
