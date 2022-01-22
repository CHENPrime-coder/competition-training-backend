package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("studios")
public class Studio {  //工作室
    private Long sid;
    private String smembers;
    private String sheadicon;
    private String sname;
}
