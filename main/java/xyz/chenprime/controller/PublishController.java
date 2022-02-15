package xyz.chenprime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.chenprime.pojo.Daily;
import xyz.chenprime.pojo.Plan;
import xyz.chenprime.service.DailyService;
import xyz.chenprime.service.PlanService;
import xyz.chenprime.utils.JwtUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 本控制器讲实现日报，计划相关接口
 */
@RestController
public class PublishController {

    private static final String PLAN_TYPE="周计划月计划学期计划";

    @Autowired
    DailyService dailyService;

    /**
     * 日报
     */
    //获取全部日报(今天的和昨天的)
    @GetMapping("/daily")
    public List<Daily> getAllDaily(){
        return dailyService.getAllDaily();
    }

    //获取指定用户的所有日报
    @GetMapping("/daily/{username}")
    public List<Daily> getDailyByUid(@PathVariable("username")String username){
        return dailyService.getDailyByUserName(username);
    }

    //发布日报
    @PostMapping("/daily")
    public Map<String,String> publishDaily(Daily daily,
                                           @CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        String dname = JwtUtils.getTokenMessage("username",token);
        daily.setReporter(dname.substring(1,dname.length()-1));
        if(dailyService.reportDaily(daily)){
            result.put("code","200");
        }else {
            result.put("code","405");
        }

        return result;
    }

    /**
     * 计划
     */

    @Autowired
    PlanService planService;

    //获取指定用户的所有计划
    @GetMapping("/plan/{username}")
    public List<Plan> getAllPlanByUserName(@PathVariable("username")String username){
        return planService.getAllPlanByUserName(username);
    }

    //发布计划
    @PostMapping("/plan")
    public Map<String,String> releasePlan(Plan plan,
                                          @CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        String pname = JwtUtils.getTokenMessage("username", token);
        plan.setPlanner(pname.substring(1,pname.length()-1));
        if(!PLAN_TYPE.contains(plan.getPtype())){
            result.put("code","405");
            return result;
        }

        if(planService.releasePlan(plan)){
            result.put("code","200");
        }else {
            result.put("code","405");
        }

        return result;
    }

    @GetMapping("/plan")
    public List<Plan> getAllPlans(){
        return planService.getAllPlans();
    }

}
