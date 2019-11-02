package com.eastday.demo.config;

import com.eastday.demo.dao.IMenuDao;
import com.eastday.demo.entity.Menu;
import com.eastday.demo.util.JwtUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Pointcut("@annotation(authentication)")
    public void annotationPointCut(Authentication authentication) {

    }

    @Before("annotationPointCut(authentication)")
    public void before(Authentication authentication){
        String permission = authentication.value();
        //权限控制业务
        Integer uid=Integer.parseInt(jwtUtils.getTokenUserId());
        List<Menu> menuList = menuDao.selectMenuByUserId(uid);
        List<String> menuCodeList=new ArrayList<>();
        for(Menu menu:menuList){
            menuCodeList.add(menu.getMenuCode());
        }
        System.out.println(menuCodeList.toString()+"---------"+permission);
        if(!menuCodeList.contains(permission)){
            throw new RuntimeException("权限不足");
        }
    }
}
