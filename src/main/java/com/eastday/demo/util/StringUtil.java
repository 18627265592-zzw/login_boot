package com.eastday.demo.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringUtil {

    private ObjectMapper objectMapper = new ObjectMapper();
    private JSONArray json = new JSONArray();

    // <----------数据类型转换---------->

    /**
     * Object转String
     *
     * @param o
     * @return
     */
    public String ObjectToString(Object o) {
        String outstr = null;
        try {
            outstr = objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outstr;
    }

    /**
     * Object转String
     *
     * @param o
     * @return
     */
    public String ObjectToString2(Object o) {
        String outstr = null;
        try {
            outstr = json.fromObject(o).toString();//objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outstr;
    }


    /**
     * String字符串转List
     *
     * @param str 字符串
     * @param c   需要转换的类
     * @return
     */
    @SuppressWarnings("deprecation")
    public List StringToList(String str, Class c) {
        // JavaType javaType =
        // objectMapper.getTypeFactory().constructParametricType(ArrayList.class, c);
        json = JSONArray.fromObject(str);
        List list = new ArrayList();
        try {
            list = JSONArray.toList(json, c);
            // list = objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * String字符串转List-jackjson
     *
     * @param str 字符串
     * @param c   需要转换的类
     * @return
     */
    @SuppressWarnings("deprecation")
    public List StringToList_jackjson(String str, Class c) {
        JavaType javaType = getCollectionType(ArrayList.class, c);
        List lst = null;
        try {
            lst = objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * String 转 Object
     *
     * @param outstr
     * @param myclass 传入的类
     * @return
     */
    public Object StringToObject(String outstr, Class myclass) {
        try {
            Object o = objectMapper.readValue(outstr, myclass);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String 转 Map
     *
     * @param outstr
     * @return
     */
    public Map StringToMap(String outstr) {
        try {
            Map o = objectMapper.readValue(outstr, Map.class);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转16进制
     *
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
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
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
