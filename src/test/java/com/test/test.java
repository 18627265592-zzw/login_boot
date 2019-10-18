package com.test;

import com.eastday.demo.Application;
import com.eastday.demo.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {


  @Value("${mobile.name}")
  private String name;

  @Value("${mobile.age}")
  private Integer age;

  @Autowired
  private IUserDao userDao;

    @Test
    public void testvg() {
      /*User user = userDao.selectByPrimaryKey(1);
      System.out.println(user);*/
      /*List<User> list=userDao.selectAll();
      for(User li:list){
        System.out.println(li.toString());
      }*/
      /*User user=new User();
      user.setPhone("BE529C996E4BE133A17602865DC3BABF");
      User user1 = userDao.selectOne(user);
      System.out.println(user1);*/
    }

    @Test
    public void test2(){
      System.out.println();
    }

}

