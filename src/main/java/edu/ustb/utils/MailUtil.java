package edu.ustb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.smtp.username}")
    private String from;

    @Value("欢迎注册去哪儿！")
    private String subject;

    @Value("<h3>欢迎注册去哪儿旅游网，请点击下方激活账户！</h3>")
    private String text;


    public void sendMail(String to, String url) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            text = text + "<a href=" + url + ">点击此处激活</a>";
            helper.setText(text, true);
            helper.setSentDate(new Date());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
