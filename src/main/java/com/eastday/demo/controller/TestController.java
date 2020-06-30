package com.eastday.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.eastday.demo.util.VerifyImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: zengzhewen
 * @create: 2020-06-28 14:40
 **/
@RestController
@RequestMapping(value = "img")
@EnableAutoConfiguration
@Slf4j
public class TestController {

    @PostMapping("createImgValidate")
    public Object createImgValidate(HttpServletRequest request){
        try {
            Integer templateNum = new Random().nextInt(4) + 1;
            Integer targetNum = new Random().nextInt(5);
            File templateFile = new File("E:\\IdeaProjects\\login_boot\\src\\main\\webapp\\template/"+templateNum+".png");
            File targetFile = new File("E:\\IdeaProjects\\login_boot\\src\\main\\webapp\\img/Pic"+targetNum+".jpg");
            Map<String, byte[]> pictureMap =  VerifyImageUtil.pictureTemplatesCut(templateFile, targetFile,
                    "png","jpg");
            // 将生成的偏移位置信息设置到session中
            request.getSession().setAttribute("xWidth",VerifyImageUtil.getX());
            log.info("xWidth:"+request.getSession().getAttribute("xWidth"));
            return JSONArray.toJSONString(pictureMap);
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        }
    }

    @PostMapping("checkImgValidate")
    public Map checkImgValidate(String moveLength,HttpServletRequest request){
        Map map =new HashMap();

        try {
            Double ImoveLength=Double.valueOf(moveLength);
            log.info("参数："+ImoveLength);
            Integer xWidth = (Integer) request.getSession().getAttribute("xWidth");

            if (xWidth == null) {
                map.put("errcode", 1);
                map.put("errmsg", "验证过期，请重试");
                return map;
            }
            //偏移值误差不超过5
            if(Math.abs(ImoveLength-xWidth) > 5){
                map.put("errcode", 2);
                map.put("errmsg", "验证不通过");
            } else {
                map.put("errcode", 0);
                map.put("errmsg", "验证通过");
                request.getSession().removeAttribute("xWidth");
                log.info("发送短信");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return map;
    }


}
