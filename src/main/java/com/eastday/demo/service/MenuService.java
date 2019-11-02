package com.eastday.demo.service;

import com.eastday.demo.dao.IMenuDao;
import com.eastday.demo.dao.IRoleAndMenuDao;
import com.eastday.demo.dao.IRoleDao;
import com.eastday.demo.dao.IUserAndRoleDao;
import com.eastday.demo.entity.Menu;
import com.eastday.demo.entity.Role;
import com.eastday.demo.entity.RoleAndMenu;
import com.eastday.demo.entity.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "menuService")
public class MenuService {

    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IRoleAndMenuDao roleAndMenuDao;

    @Autowired
    private IUserAndRoleDao userAndRoleDao;

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

    public String addRole(String roleName){
        String returnstr="true";
        try {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setCreateTime(new Date());
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

    public String updateMenuByRoleId(List<RoleAndMenu> list){
        String returnstr="true";
        try {
            RoleAndMenu roleAndMenu = new RoleAndMenu();
            //取roleId
            roleAndMenu.setRoleId(list.get(0).getRoleId());
            //先删除角色对应所有权限
            roleAndMenuDao.delete(roleAndMenu);
            //重新添加角色权限
            roleAndMenuDao.insertList(list);
        }catch (Exception e){
            returnstr="false";
            e.printStackTrace();
        }
        return returnstr;
    }


    public String updateUserAndRole(Integer userId,Integer roleId){
        String returnstr="true";
        try {
            UserAndRole userAndRole = new UserAndRole();
            userAndRole.setUserId(userId);
            UserAndRole userAndRole1 = userAndRoleDao.selectOne(userAndRole);
            if(userAndRole1==null){
                userAndRole.setRoleId(roleId);
                userAndRoleDao.insertSelective(userAndRole);
            }else{
                userAndRoleDao.updateByPrimaryKeySelective(userAndRole1);
            }

        }catch (Exception e){
            returnstr="false";
            e.printStackTrace();
        }
        return returnstr;
    }

}
