package xyz.chenprime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.chenprime.pojo.Task;
import xyz.chenprime.service.TaskService;
import xyz.chenprime.utils.JwtUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService service;

    /**
     * 发布任务Controller，token自动获取
     * @param task 任务对象
     * @return code值
     */
    @PostMapping("/task")
    public Map<String,String> publishTask(Task task, @CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        if(JwtUtils.getTokenMessage("role",token).equals("学生")){
            //权限不足
            result.put("code","403");
        }else {
            String username = JwtUtils.getTokenMessage("username",token);
            Map<String,String> addRes = service.addTask(task,username);
            if(addRes.get("code").equals("405")){
                //发布失败
                result.put("code","405");
            }else {
                //成功了
                result.put("code","200");
                if(addRes.size()>1){
                    result.put("filename",addRes.get("filename"));
                }
            }
        }
        return result;
    }

    @GetMapping("/task")
    public List<Task> getAllTask(){
        return service.getAllTasks();
    }

    @PutMapping("/task")
    public Map<String,String> updateProgress(@RequestParam("progress") String progress,@RequestParam("tid")Long tid,
                                             @CookieValue("token") String token){
        Map<String,String> result = new HashMap<>();
        if(JwtUtils.getTokenMessage("role",token).equals("老师")){
            result.put("code","403");
        }else {
            if(service.updateTaskProgress(progress,tid)){
                result.put("code","200");
            }else {
                result.put("code","406");
            }
        }
        return result;
    }

    @DeleteMapping("/task")
    public Map<String,String> deleteTask(@RequestParam("tid")Long tid,@CookieValue("token")String token){
        Map<String,String> result = new HashMap<>();
        if(JwtUtils.getTokenMessage("role",token).equals("学生")){
            result.put("code","403");
        }else {
            if(service.deleteTask(tid)){
                result.put("code","200");
            }else {
                result.put("code","407");
            }
        }
        return result;
    }

}
