package com.eastday.demo.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@Slf4j
public class KaptchaController {

    /**
     * 验证码工具
     */
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getKaptcha")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] kaptchaArr = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("rightCode", createText);
            log.debug("图像验证码："+request.getSession().getAttribute("rightCode"));
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaArr = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(kaptchaArr);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login/success")
    public String index(){
        return "index";
    }




}
