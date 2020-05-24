package edu.ustb.controller;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.service.IRouteService;
import edu.ustb.vo.PageBean;
import edu.ustb.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private IRouteService routeService;
    private final int pageSize = 4;

    @RequestMapping("/find")
    @ResponseBody
    public List<Route> find(int cid) {
        return routeService.find(cid);
    }

    @RequestMapping("/pageQueryByRouteName")
    @ResponseBody
    public PageBean<Route> pageQueryByRouteName(Route route, @RequestParam(defaultValue = "1") int currentPage) {
        return routeService.search(route, currentPage,pageSize);
    }

    @RequestMapping("/pageQueryByReq")
    @ResponseBody
    public PageBean<Route> pageQueryByReq(String rname, RouteReq routeReq, @RequestParam(defaultValue = "1") int currentPage) {
        Route route = new Route();
        route.setRname(rname);
        routeReq.setRoute(route);
        return routeService.searchByReq(routeReq, currentPage, pageSize);
    }

    @RequestMapping("/findOne")
    @ResponseBody
    public Route findOne(Route route) {
        return routeService.findOne(route);
    }

    @RequestMapping("/isFavorite")
    @ResponseBody
    public boolean isFavorite(Route route, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return routeService.isFavorite(route, user);
    }

    @RequestMapping("/addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(Route route, HttpServletRequest request) {
        ResultInfo info = new ResultInfo();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            info.setFlag(false);
            info.setData(1);
            info.setErrorMsg("您尚未登录，请登录！");
        } else {
            boolean flag = routeService.addFavorite(route, user);
            if (!flag) {
                info.setFlag(false);
                info.setData(2);
                info.setErrorMsg("添加失败！");
            } else {
                info.setErrorMsg("添加成功！");
                info.setFlag(true);
            }
        }
        return info;
    }

    @RequestMapping("/getFavorite")
    @ResponseBody
    public PageBean<Route> getFavorite(User user, @RequestParam(defaultValue = "1") int currentPage) {
        return routeService.getByFavorite(user, currentPage, pageSize);
    }
}
