package com.eastday.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eastday.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by admin on 2019/10/9.
 */
@Component
public class JwtUtils extends ConstantKey {



    /**
     * 获取token
     * @param user 用户对象
     * @return
     */
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 2;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        //用户手机号作为秘钥
        token = JWT.create().withAudience(user.getUid().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUserPhone()));
        return token;
    }


    /**
     * token中获取uid
     * @return
     */
    public String getTokenUserId() {
        String token = getRequest().getHeader("accessToken");// 从 http 请求头中取出 accessToken
        String uid = JWT.decode(token).getAudience().get(0);
        return uid;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    //token过期测试
                    /*try {
                        User user2=jwt.decode(token,User.class);
                        if(user2==null){
                            System.out.println("token过期");
                        }else{
                            System.out.println("-----"+user2.getCode());
                            System.out.println("验证通过");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/

}
