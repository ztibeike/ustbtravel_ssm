package edu.ustb.service.impl;

import edu.ustb.dao.IUserDao;
import edu.ustb.domain.User;
import edu.ustb.service.IUserService;
import edu.ustb.utils.MailUtil;
import edu.ustb.utils.UuidUtil;
import edu.ustb.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        System.out.println("UserService.findAll()执行了...");
        return userDao.findAll();
    }

    @Override
    public User findByName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1 != null && passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            return user1;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1 != null) {
            return false;
        }
        user.setStatus("N");
        String activeCode = UuidUtil.getUuid();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setCode(activeCode);
        user.setPassword(encryptedPassword);
        userDao.saveUser(user);
        String url = "http://localhost:8080/ustbtravel_ssm/user/active?username=" + user.getUsername() + "&activeCode=" + activeCode;
        mailUtil.sendMail(user.getEmail(), url);
        return true;
    }

    @Override
    public boolean active(User user, String activeCode) {
        if (user == null) {
            return false;
        }
        User user1 = userDao.findByCode(activeCode);
        if (!user1.getUsername().equals(user.getUsername())) {
            return false;
        }
        userDao.updateStatus(user1);
        return true;
    }
}
