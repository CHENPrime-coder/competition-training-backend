package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("task")
public class Task { //任务
    private Long tid;
    private String publisher;
    private String tbody;
    private String tname;
    private String tfiles;
    private String progress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;
    private String tmembers;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tdate;
}
