package edu.ustb.service.impl;

import edu.ustb.dao.IRouteDao;
import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.service.IRouteService;
import edu.ustb.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;

    @Override
    public List<Route> find(int cid) {
        return routeDao.findByCid(cid);
    }

    @Override
    public PageBean<Route> search(Route route, int currentPage, int pageSize) {
        if (route == null) {
            return null;
        }
        int totalCount = routeDao.getTotalCount(route);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pageBean = new PageBean<>();
        List<Route> routes = routeDao.search(route, currentPage, pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(routes);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public PageBean<Route> searchByReq(RouteReq routeReq, int currentPage, int pageSize) {
        if (routeReq == null) {
            return null;
        }
        int totalCount = routeDao.getTotalCountByReq(routeReq);
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pageBean = new PageBean<>();
        List<Route> routes = routeDao.searchByReq(routeReq, currentPage, pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(routes);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(Route route) {
        return routeDao.findOne(route);
    }

    @Override
    public boolean isFavorite(Route route, User user) {
        if (user == null) {
            return false;
        }
        return routeDao.isFavorite(route, user);
    }

    @Override
    public boolean addFavorite(Route route, User user) {
       boolean flag1 = routeDao.addFavoriteToUser(route, user) > 0;
       boolean flag2 = routeDao.addFavorite(route) > 0;
       return flag1 && flag2;
    }

    @Override
    public PageBean<Route> getByFavorite(User user, int currentPage, int pageSize) {
        if (user == null) {
            return null;
        }
        int totalCount = routeDao.getFavoriteCount(user);
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pageBean = new PageBean<>();
        List<Route> routes = routeDao.getByFavorite(user,currentPage,pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(routes);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
