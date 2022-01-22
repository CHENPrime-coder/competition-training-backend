package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("daily")
public class Daily {   //日报
    private String dname;
    private Long did;
    private Date ddate;
    private String reporter;
    private String dbody;
}
