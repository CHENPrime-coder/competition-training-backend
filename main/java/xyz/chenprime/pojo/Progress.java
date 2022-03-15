package xyz.chenprime.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Progress {

    Long tid;
    Long proid;
    Integer progress;
    Date prodate;
    String remarks;
    String username;

}
