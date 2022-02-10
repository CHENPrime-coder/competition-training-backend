package xyz.chenprime.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.chenprime.exception.BadTokenException;
import xyz.chenprime.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * App
 * token生成与验证解析
 */
@Component
public class JwtUtils {

//token过期时间 ：     1 天
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 1;
//token密钥，机密
    private static final String KEY="CHENPrime";

    /**
     * 生成token
     * header,payload,signatrue
     * @param uid 用户id作为payload，不可为空
     * @param role 用户角色payload，非空
     */
    public static String createToken(Long uid,String role,String username) throws Exception{

        //token注册日期
        Date signDate = new Date();
        //过期时间
        Calendar now = Calendar.getInstance();
        now.add(calendarField,calendarInterval);//一天后
        Date expiresDate = now.getTime();
        //header的HashMap
        Map<String,Object> myHeader = new HashMap<>();
        myHeader.put("alg","HS256");//指定一个算法
        myHeader.put("typ","JWT");

        //创建token
        String token = JWT.create().withHeader(myHeader)//header
                .withClaim("uid",uid)
                .withClaim("role",role)
                .withClaim("username",username)
                .withIssuedAt(signDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(KEY));

        return token;
    }

    /**
     * 验证token，失败就抛出异常返回空
     * @param token 客户端传来的token，用于验证
     */
    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
            jwt = verifier.verify(token);//解密
        }catch (Exception e){
            throw new BadTokenException();
        }
        return jwt != null ? jwt.getClaims() : null;
    }

    /**
     * 获取token里面payload信息
     * @param key 键，获取对应的值
     * @param token 客户端传来的token，用于获取payload
     */
    public static String getTokenMessage(String key,String token){
        Map<String,Claim> claims = verifyToken(token);
        Claim value = claims != null ? claims.get(key) : null;
        if(null==value){
            //验证异常
            throw new BadTokenException();
        }
        return String.valueOf(value);
    }

}
