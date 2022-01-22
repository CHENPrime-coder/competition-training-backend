package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.TaskMapper;
import xyz.chenprime.pojo.Task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    TaskMapper mapper;

    public List<Task> getAllTasks(){
        return mapper.getAllTasks();
    }

    /**
     * 发布任务Service
     * @param task 任务对象，里面的发布人值应为空
     * @param username 用户名，作为发布人
     * @return 返回0就是失败了
     */
    public Map<String,String> addTask(Task task, String username){
        Map<String,String> result = new HashMap<>();
        task.setPublisher(username);
        //确保文件名不重复
        Date date = new Date();
        long time = date.getTime();
        if(task.getTfiles()!=null){
            int i = task.getTfiles().lastIndexOf('.');
            String filename = task.getTfiles().substring(0,i);
            String surfix = task.getTfiles().substring(i);
            task.setTfiles(filename+time+surfix);
        }
        int res = mapper.addTask(task);
        if(res==0){
            //上传失败
            result.put("code","405");
        }
        if(task.getTfiles()!=null){
            result.put("code","200");
            result.put("filename",task.getTfiles());
        }else {
            result.put("code","200");
        }
        return result;
    }

    public boolean updateTaskProgress(String progress,Long tid){
        return mapper.updateTaskProgress(progress,tid)!=0;
    }

    public boolean deleteTask(Long tid){
        return mapper.deleteTask(tid)!=0;
    }

}
