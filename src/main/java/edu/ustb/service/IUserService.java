package edu.ustb.service;

import edu.ustb.domain.User;
import edu.ustb.vo.ResultInfo;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findByName(String username);

    User findByUsernameAndPassword(User user);

    boolean register(User user);

    boolean active(User user, String activeCode);
}
