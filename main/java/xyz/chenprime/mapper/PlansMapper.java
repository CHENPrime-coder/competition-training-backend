package xyz.chenprime.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.chenprime.pojo.Plan;

import java.util.List;

@Mapper
public interface PlansMapper {

    @Select("select * from plans where planner=#{planner}")
    List<Plan> getAllPlanByUserName(String planner);

    @Insert("insert into plans(pname, pbody, ptype, planner) VALUES " +
            "(#{pname},#{pbody},#{ptype},#{planner})")
    int releasePlan(Plan plan);

    @Select("select * from plans")
    List<Plan> getAllPlans();

}
