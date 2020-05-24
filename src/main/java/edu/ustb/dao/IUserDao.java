package edu.ustb.dao;

import edu.ustb.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    List<User> findAll();

    User findByUsername(String username);

    void saveUser(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
