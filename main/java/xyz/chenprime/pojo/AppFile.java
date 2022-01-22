package xyz.chenprime.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("files")
public class AppFile {  //文件
    private String name;
    private String url;
    private String type;
    private String oldname;//用户下载到的显示的是oldname，服务器保存处理过的文件名name
    private Long fid;
}
