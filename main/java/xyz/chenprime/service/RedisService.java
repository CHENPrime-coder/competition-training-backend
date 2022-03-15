package xyz.chenprime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import xyz.chenprime.utils.DateUtils;

import java.time.Duration;
import java.util.*;

@Service
public class RedisService {

    @Autowired
    @Qualifier("redisTemplateForDailyPunch")
    RedisTemplate<String,String> redisTemplateForDailyPunch;

    @Autowired
    @Qualifier("redisTemplateFor356Punch")
    RedisTemplate<String,String> redisTemplateFor356Punch;

//    日报打卡
    public void dailyClockIn(String username) {
        System.out.println(username);
        redisTemplateForDailyPunch.opsForValue().setIfAbsent("punch_"+username,username, Duration.ofDays(10));
    }

//    获取昨天日报打卡的用户名
    public List<String> getAllPunchUsernames(){
        List<String> list = new ArrayList<>();
        Set<String> keys = redisTemplateForDailyPunch.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("punch_*").count(1000).build());
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
        if(keys!=null){
            for(String key : keys){
                list.add(redisTemplateForDailyPunch.opsForValue().get(key));
            }
        }
        return list;
    }

    /**
     * 356天 发布不同类型的信息记录进redis（daily,monthpaln,weekplan,yearplan）
     * @param type 发布的类型 daily,周计划,月计划,学期计划
     * @param username 用户名
     * @param date 日期 yyyy-mm-dd
     */
    public void PublishRecordWithType(String type,String username,String date){
        switch (type){
            case "周计划":
                type = "weekplan";
                break;
            case "月计划":
                type = "monthpaln";
                break;
            case "学期计划":
                type = "yearplan";
                break;
        }
        String yyyyMM = date.split("-")[0]+date.split("-")[1]; //202203
        int dd = Integer.parseInt(date.split("-")[2])-1; //offset偏移量 13 (14号)
        redisTemplateFor356Punch.opsForValue().setBit(username+":"+type+":"+yyyyMM, dd, true);
    }

    public Map<String,String> getAllPublishInfoByYearAndMonth(String yyyyMM, String username){
        StringBuilder daily = new StringBuilder();
        StringBuilder monthpaln = new StringBuilder();
        StringBuilder weekplan = new StringBuilder();
        StringBuilder yearplan = new StringBuilder();

        for (int i=0;i< DateUtils.getDaysCountByFormatter(yyyyMM);i++){
            daily.append(redisTemplateFor356Punch.opsForValue().getBit(username+":daily:"+yyyyMM, i)?1:0);
            monthpaln.append(redisTemplateFor356Punch.opsForValue().getBit(username+":monthplan:"+yyyyMM, i)?1:0);
            weekplan.append(redisTemplateFor356Punch.opsForValue().getBit(username+":weekplan:"+yyyyMM, i)?1:0);
            yearplan.append(redisTemplateFor356Punch.opsForValue().getBit(username+":yearplan:"+yyyyMM, i)?1:0);
        }

        Map<String,String> result = new HashMap<>();
        result.put("daily", String.valueOf(daily));
        result.put("monthplan", String.valueOf(monthpaln));
        result.put("weekplan", String.valueOf(weekplan));
        result.put("yearplan", String.valueOf(yearplan));
        return result;
    }

}
