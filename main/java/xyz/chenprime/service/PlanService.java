package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.PlansMapper;
import xyz.chenprime.pojo.Plan;

import java.util.List;
import java.util.Map;

@Service
public class PlanService {

    @Autowired
    PlansMapper mapper;

    public List<Plan> getAllPlanByUserName(String username){
        return mapper.getAllPlanByUserName(username);
    }

    public boolean releasePlan(Plan plan){
        return mapper.releasePlan(plan)!=0;
    }

    public List<Plan> getAllPlans(){
        return mapper.getAllPlans();
    }

}
