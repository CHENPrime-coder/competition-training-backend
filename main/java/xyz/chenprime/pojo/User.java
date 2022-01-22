package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User { //用户
    private String username;
    private String password;
    private String headicon;
    private String telnumber;
    private String role;
    private Long uid;
}
