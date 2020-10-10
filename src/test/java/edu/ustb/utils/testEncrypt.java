package edu.ustb.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testEncrypt {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encrypt() {
        String pwd = "lihui520";
        System.out.println(passwordEncoder.encode(pwd));
    }
}
