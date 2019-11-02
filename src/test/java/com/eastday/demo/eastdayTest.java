package com.eastday.demo;

import com.eastday.demo.dao.IUserAndRoleDao;
import com.eastday.demo.entity.UserAndRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class eastdayTest {

    @Autowired
    private IUserAndRoleDao userAndRoleDao;

    @Test
    public void selectPemissionByUserId(){
        UserAndRole userAndRole = new UserAndRole();
        userAndRole.setUserId(6);
        UserAndRole userAndRole1 = userAndRoleDao.selectOne(userAndRole);
        System.out.println(userAndRole1.toString());

    }

}
