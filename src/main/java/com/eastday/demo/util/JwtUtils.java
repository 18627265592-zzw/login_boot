package com.eastday.demo.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2019/10/9.
 */
@Component
public class JwtUtils extends RedisKey {

    /**
     * 密钥
     */
    private static  final String SECRET="dfw";
    /**
     * 默认字段key:exp
     */
    private static final String EXP="exp";
    /**
     * 默认字段key:payload
     */
    private static final String PAYLOAD="payload";


    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 纯数字随机串码
     *
     * @param number 多少位
     * @return 运行结果
     */
    public static String NumberCode(int number) {
        String str = "";
        Random r = new Random();
        int[] ary = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < number; i++) {
            str += ary[r.nextInt(ary.length)];
        }
        return str;
    }


    /**
     * 加密
     * @param object 加密数据
     * @param maxTime 有效期（毫秒数）
     * @param <T>
     * @return
     */
    public static <T> String encode(T object,long maxTime){
        try{
            final JWTSigner signer=new JWTSigner(SECRET);
            final Map<String ,Object> data=new HashMap<>(10);
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonString=objectMapper.writeValueAsString(object);
            data.put(PAYLOAD,jsonString);
            data.put(EXP,System.currentTimeMillis()+maxTime);
            return signer.sign(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 数据解密
     * @param jwt 解密数据
     * @param tClass 解密类型
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T decode(String jwt,Class<T> tClass) throws Exception{
        final JWTVerifier jwtVerifier=new JWTVerifier(SECRET);
        final Map<String,Object> data=jwtVerifier.verify(jwt);
        //判断数据是否超时或者符合标准
        if(data.containsKey(EXP)&&data.containsKey(PAYLOAD)){
            long exp= (long) data.get(EXP);
            long currentTimeMillis=System.currentTimeMillis();
            if(exp>currentTimeMillis){
                String json= (String) data.get(PAYLOAD);
                ObjectMapper objectMapper=new ObjectMapper();
                return objectMapper.readValue(json,tClass);
            }else {
                return null;
                //throw new UserTimeoutException("用户登录超时");
            }
        }else {
            return null;
            //throw new UserErrorTokenException("用户token错误");
        }
    }

}
