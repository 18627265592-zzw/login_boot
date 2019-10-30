package com.eastday.demo.service;

import com.eastday.demo.dao.IMenuDao;
import com.eastday.demo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "menuService")
public class MenuService {

    @Autowired
    private IMenuDao menuDao;

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


}
