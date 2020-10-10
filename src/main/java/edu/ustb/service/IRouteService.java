package edu.ustb.service;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.vo.PageBean;

import java.util.List;

public interface IRouteService {

    List<Route> find(int cid);

    PageBean<Route> search(Route route, int currentPage, int pageSize);

    PageBean<Route> searchByReq(RouteReq routeReq, int currentPage, int pageSize);

    Route findOne(Route route);

    boolean isFavorite(Route route, User user);

    boolean addFavorite(Route route, User user);

    PageBean<Route> getByFavorite(User user, int currentPage, int pageSize);
}
