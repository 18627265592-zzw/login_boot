package com.eastday.demo.service;

import com.eastday.demo.dao.IMenuDao;
import com.eastday.demo.dao.IRoleDao;
import com.eastday.demo.entity.Menu;
import com.eastday.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "menuService")
public class MenuService {

    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private IRoleDao roleDao;

    public List<Menu> findMenuByUserId(Integer uid){
        List<Menu> allMenus = menuDao.selectMenuByUserId(uid);
        List<Menu> menus=new ArrayList<>();
        for(Menu menu: allMenus){
            if(menu.getParentId()==0 && menu.getLever()==1){  //添加1级菜单
                menus.add(menu);
            }
        }
        for(Menu menu: menus){
            menu.setChildMenus(getChild(menu.getMenuId(),allMenus));  //为1级菜单添加2级菜单
        }
        return menus;
    }

    public List<Menu> getChild(int menuId,List<Menu> allMenus){
        List<Menu> childMenus=new ArrayList<>();
        for(Menu menu:allMenus){
            if(menu.getParentId()!=0){
                if(menu.getParentId()==menuId){
                    childMenus.add(menu);
                }
            }
        }
        if(childMenus.size()==0){
            return null;
        }
        return childMenus;
    }


    public Map<Object,Object> findRoleAndMenu(){
        Map<Object,Object> map = new HashMap<>();
        List<Role> roleList = roleDao.selectAll();
        for(Role role:roleList){
            List<String> roleNameList = new ArrayList<>();
            List<Menu> menus = menuDao.selectMenuByRoleId(role.getRoleId());
            for(Menu menu:menus){
                roleNameList.add(menu.getMenuName());
            }
            map.put(role,roleNameList);
        }
        return map;
    }

    public String addRole(Role role){
        String returnstr="true";
        try {
            roleDao.insertSelective(role);
        }catch (Exception e){
            returnstr="false";
            e.printStackTrace();
        }
        return returnstr;
    }

    public List<Menu> findMenuByRoleId(Integer roleId){
        return menuDao.selectMenuByRoleId(roleId);
    }


}
