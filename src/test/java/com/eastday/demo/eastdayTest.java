package com.eastday.demo;

import com.eastday.demo.dao.IUserAndRoleDao;
import com.eastday.demo.util.DesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class eastdayTest {

    @Autowired
    private IUserAndRoleDao userAndRoleDao;

    @Test
    public void test(){
        Map map = new HashMap();
        map.put("name","jack");
        map.put("age",18);
        System.out.println(map.toString());
    }

    /**
     * 验证用户名，只能包含数字，字母，汉字。长度4-16位
     * @param username
     * @return 验证通过true，失败false
     */
    public Boolean checkUsername(String username){
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
        Matcher m = p.matcher(username);
        if(m.matches()){
            Integer number = String_length(username);
            if(number >= 4 && number <= 16){
                return true;
            }
        }
        return false;
    }

    /**
     * 返回字符串的字符长度
     * @param value
     * @return
     */
    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    @Test
    public void test2(){
        String encrypt = DesUtil.encrypt("18627265592");
        System.out.println(encrypt);
    }






}
