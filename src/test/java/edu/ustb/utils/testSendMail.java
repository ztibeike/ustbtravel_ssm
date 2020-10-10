package edu.ustb.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testSendMail {

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void testSend() {
        mailUtil.sendMail("tomzeng9@163.com", "123");
    }
}
