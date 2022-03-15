package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.ProgressMapper;
import xyz.chenprime.mapper.TaskMapper;
import xyz.chenprime.pojo.Progress;
import xyz.chenprime.pojo.Task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    TaskMapper mapper;

    @Autowired
    ProgressMapper progressMapper;

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

    /**
     * 更新任务进度，包含明细
     * @param progress 每次更新的记录
     * @param newPro 任务总进度
     * @return true为更新成功
     */
    public boolean updateTaskProgress(Progress progress,String newPro){
        return mapper.updateTaskProgress(newPro,progress.getTid())!=0 &&
                progressMapper.UpdateProgress(progress)!=0;
    }


    /**
     * 删除任务，并删除相关的任务明细
     * @param tid 任务id
     * @return 返回true就是删除成功了
     */
    public boolean deleteTask(Long tid){
        return mapper.deleteTask(tid)!=0 &&
                progressMapper.DeleteProgressByTid(tid)!=0;
    }

    public List<Progress> getAllProgressByTid(Long tid){
        return progressMapper.getAllDetailsProgressByTid(tid);
    }

}
