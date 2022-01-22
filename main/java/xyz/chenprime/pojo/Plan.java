package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("plans")
public class Plan { //计划
    private Long pid;
    private String pname;
    private String pbody;
    private String ptype;
    private String planner;
}
