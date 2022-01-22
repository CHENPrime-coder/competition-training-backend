package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chenprime.mapper.DailyMapper;
import xyz.chenprime.pojo.Daily;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyService {

    @Autowired
    DailyMapper mapper;

    /**
     * 获取今天和昨天的日期，提供给mapper
     * @return 昨天和今天的日报查询结果
     */
    public List<Daily> getAllDaily(){
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_MONTH);
        //格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String now = sdf.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH,i-1);
        String yesterday = sdf.format(calendar.getTime());
        return mapper.getAllDaily(now,yesterday);
    }

    /**
     * 获取指定用户的所有日报
     * @param username 用户名
     * @return 指定用户写的所有日报
     */
    public List<Daily> getDailyByUserName(String username){
        return mapper.getDaiilyByUserName(username);
    }

    /**
     * 发布日报
     * @param daily 日报对象
     * @return 返回为true就是发布成功，反之失败
     */
    public boolean reportDaily(Daily daily){
        return mapper.reportDaily(daily)!=0;
    }

}
