package edu.ustb.service;

import edu.ustb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testUserService {

    @Autowired
    private IUserService userService;

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByName() {
        User user = userService.findByName("admin6666");
        System.out.println(user);
    }
}
