package edu.ustb.controller;

import edu.ustb.domain.User;
import edu.ustb.service.IUserService;
import edu.ustb.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "success";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultInfo login(User user, String check, HttpServletRequest request) {
        String check_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        if (check == null || check_server == null || !check_server.equalsIgnoreCase(check)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
        } else {
            User user1 = userService.findByUsernameAndPassword(user);
            if (user1 == null) {
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误!");
            } else if (user1.getStatus() == null || user1.getStatus().equals("N")) {
                info.setFlag(false);
                info.setErrorMsg("用户未激活!");
            } else {
                info.setFlag(true);
                info.setErrorMsg("登陆成功!");
                request.getSession().setAttribute("user", user1);
            }
        }
        return info;
    }

    @RequestMapping("/findOne")
    @ResponseBody
    public User findOne(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @RequestMapping("/register")
    @ResponseBody
    public ResultInfo register(User user, String check, HttpServletRequest request) {
        ResultInfo info = new ResultInfo();
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        if (checkCode == null || !checkCode.equalsIgnoreCase(check)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            return info;
        }
        boolean flag = userService.register(user);
        if (!flag) {
            info.setFlag(false);
            info.setErrorMsg("用户名已存在！");
        } else {
            info.setFlag(true);
            info.setData("注册成功，请查收邮件激活账户!");
        }
        return info;
    }

    @RequestMapping("/active")
    public String active(User user, String activeCode) {
        if (activeCode != null) {
            boolean flag = userService.active(user, activeCode);
            if (flag) {
                return "active_ok";
            }
        }
        return "active_fail";
    }
}
