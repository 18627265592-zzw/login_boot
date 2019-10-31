package com.eastday.demo.controller;

import com.eastday.demo.config.AuthenticationInterceptor;
import com.eastday.demo.entity.Menu;
import com.eastday.demo.service.MenuService;
import com.eastday.demo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
@EnableAutoConfiguration
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtUtils jwt;

    /**
     * 获取用户菜单权限列表
     * @param uid 用户id
     * @return 用户权限列表
     */
    @GetMapping(value = "/{uid}")
    @AuthenticationInterceptor.UserLoginToken
    public List<Menu> findMenuByUserId(@PathVariable Integer uid){
        System.out.println(jwt.getTokenUserId());
        return menuService.findMenuByUserId(uid);
    }

}
