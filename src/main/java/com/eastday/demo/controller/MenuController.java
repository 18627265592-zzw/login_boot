package com.eastday.demo.controller;

import com.eastday.demo.entity.Menu;
import com.eastday.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 获取用户菜单权限列表
     * @param uid 用户id
     * @return 用户权限列表
     */
    @GetMapping(value = "/{uid}")
    @PreAuthorize("hasAuthority('user_permission')")
    public List<Menu> findMenuByUserId(@PathVariable Integer uid){
        return menuService.findMenuByUserId(uid);
    }

}
