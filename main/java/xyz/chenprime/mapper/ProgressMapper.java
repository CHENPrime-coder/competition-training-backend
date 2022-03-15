package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.chenprime.pojo.Progress;

import java.util.List;

@Mapper
public interface ProgressMapper {

    @Select("select * from progress where tid = #{tid}")
    List<Progress> getAllDetailsProgressByTid(Long tid);

    @Insert("insert into progress (tid, progress, remarks, username) values " +
            "(#{tid},#{progress},#{remarks},#{username});")
    int UpdateProgress(Progress progress);

    @Delete("delete from progress where tid = #{tid}")
    int DeleteProgressByTid(Long tid);

}
