package com.test;

import com.eastday.demo.Application;
import com.eastday.demo.entity.User;
import com.eastday.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

  @Autowired
  private UserMapper userMapper;

  @Value("${mobile.name}")
  private String name;

  @Value("${mobile.age}")
  private Integer age;

    @Test
    public void testvg() {
      User user = userMapper.selectByPrimaryKey(1);
      System.out.println(user);
    }

    @Test
    public void test2(){
      System.out.println(name+"----"+age);
    }

}

