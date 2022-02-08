package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.*;
import xyz.chenprime.pojo.Task;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("select * from task")
    List<Task> getAllTasks();

    @Insert("insert into task(publisher, tbody, tname, tfiles, progress, startdate, enddate, tmembers) values " +
            "(#{publisher},#{tbody},#{tname},#{tfiles},#{progress},#{startdate},#{enddate},#{tmembers})")
    int addTask(Task task);

    @Update("update task set progress = #{progress} where tid=#{tid};")
    int updateTaskProgress(String progress,Long tid);

    @Delete("delete from task where tid=#{tid}")
    int deleteTask(Long tid);
}
