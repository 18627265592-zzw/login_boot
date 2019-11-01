package com.eastday.demo.controller;

import com.eastday.demo.config.Authentication;
import com.eastday.demo.config.UserLoginToken;
import com.eastday.demo.entity.Menu;
import com.eastday.demo.entity.Role;
import com.eastday.demo.service.MenuService;
import com.eastday.demo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/menu")
@EnableAutoConfiguration
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtUtils jwt;

    //默认先经过过滤器校验是否登录，再aop验证权限
    /**
     * 获取用户菜单权限列表
     * @param uid 用户id
     * @return 用户权限列表
     */
    @GetMapping(value = "/{uid}")
    @UserLoginToken
    @Authentication(value = "user_permission")
    public List<Menu> findMenuByUserId(@PathVariable Integer uid){
        System.out.println(jwt.getTokenUserId());
        //判断参数id与token中uid是否一致
        if(uid == Integer.parseInt(jwt.getTokenUserId())){
            return menuService.findMenuByUserId(uid);
        }else{
            return null;
        }
    }


    /**
     * 查询所有角色及对应菜单列表
     * @return 角色对象:权限名称集合
     */
    @GetMapping(value = "/roleAndMenu")
    @UserLoginToken
    @Authentication(value = "user_permission")
    public Map<Object,Object> findRoleAndMenu(){
        return menuService.findRoleAndMenu();
    }


    /**
     * 新增角色
     * @param role 角色对象
     * @return 'true':新增成功  'false':新增失败
     */
    @PostMapping(value = "/role")
    @UserLoginToken
    @Authentication(value = "user_permission")
    public String addRole(Role role){
        return menuService.addRole(role);
    }

    /**
     * 查询角色对象权限信息
     * @param roleId
     * @return 权限对象集合
     */
    @GetMapping(value = "/role/{roleId}")
    @UserLoginToken
    @Authentication(value = "user_permission")
    public List<Menu> findMenuByRoleId(@PathVariable Integer roleId){
        return menuService.findMenuByRoleId(roleId);
    }


    /**
     * 角色权限调整
     * @param roleId 角色id
     * @param roleMenuIds 权限id字符串，以','分割
     * @return
     */
    public String updateMenuByRoleId(Integer roleId,String roleMenuIds){
        String[] ids =roleMenuIds.split(",");
        return null;
    }


    //用户角色调整


}
