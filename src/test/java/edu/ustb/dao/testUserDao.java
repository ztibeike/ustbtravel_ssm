package edu.ustb.dao;

import edu.ustb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testUserDao {
    @Autowired
    public IUserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("hehe");
        user.setPassword("123");
        user.setBirthday("2019-01-01");
        user.setCode("123");
        user.setSex("男");
        user.setStatus("Y");
        userDao.saveUser(user);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        String username = "hehe";
        String password = "123";
        User user = userDao.findByUsernameAndPassword(username, password);
        System.out.println(user);
    }

}
