package edu.ustb.dao;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testRouteDao {

    @Autowired
    private IRouteDao routeDao;

    @Test
    public void testGetTotalCount() {
        Route route = new Route();
        route.setCid(5);
        route.setRname("春节");
        int count = routeDao.getTotalCount(route);
        System.out.println(count);
    }

    @Test
    public void testGetTotalCountByReq() {
        RouteReq req = new RouteReq();
        Route route = new Route();
        route.setCid(5);
        route.setRname("春节");
        req.setRoute(route);
        req.setMaxPrice(3000);
        req.setMinPrice(2000);
        int count = routeDao.getTotalCountByReq(req);
        System.out.println(count);
    }

    @Test
    public void testSearch() {
        Route route = new Route();
        route.setCid(5);
//        route.setRname("春节");
        List<Route> routes = routeDao.search(route, 1, 4);
        for (Route route1 : routes) {
            System.out.println(route1);
        }
    }

    @Test
    public void findOne() {
        Route route = new Route();
        route.setRid(315);
        route.setCid(5);
        route.setSid(1);
        route = routeDao.findOne(route);
        System.out.println(route);
    }

    @Test
    public void testIsFavorite() {
        Route route = new Route();
        route.setRid(1);
        User user = new User();
        user.setUid(2);
        System.out.println(routeDao.isFavorite(route, user));
    }

    @Test
    public void testAddFavoriteToUser() {
        Route route = new Route();
        route.setRid(1);
        User user = new User();
        user.setUid(2);
        routeDao.addFavoriteToUser(route, user);
    }

    @Test
    public void testAddFavorite() {
        Route route = new Route();
        route.setRid(1);
        routeDao.addFavorite(route);
    }

    @Test
    public void testSearchByReq() {
        RouteReq req = new RouteReq();
        Route route = new Route();
        route.setCid(5);
        route.setRname("春节");
        req.setRoute(route);
        req.setMaxPrice(3000);
        req.setMinPrice(2000);
        List<Route> routes = routeDao.searchByReq(req, 1, 4);
        for (Route route1 : routes) {
            System.out.println(route1);
        }
    }

    @Test
    public void testGetByFavorite() {
        User user = new User();
        user.setUid(10);
        List<Route> routes = routeDao.getByFavorite(user, 3, 4);
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    @Test
    public void testFindByCid() {
        List<Route> routes = routeDao.findByCid(5);
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    @Test
    public void testGetFavoriteCount() {
        User user = new User();
        user.setUid(10);
        System.out.println(routeDao.getFavoriteCount(user));
    }
}
