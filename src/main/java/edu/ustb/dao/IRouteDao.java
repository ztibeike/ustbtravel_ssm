package edu.ustb.dao;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRouteDao {

    int getTotalCount(Route route);

    int getTotalCountByReq(RouteReq routeReq);

    List<Route> search(@Param("route") Route route, @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    Route findOne(Route route);

    boolean isFavorite(@Param("route") Route route, @Param("user") User user);

    int addFavoriteToUser(@Param("route") Route route, @Param("user") User user);

    int addFavorite(Route route);

    List<Route> searchByReq(@Param("routeReq") RouteReq routeReq,  @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    List<Route> getByFavorite(@Param("user") User user, @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    List<Route> findByCid(int cid);

    int getFavoriteCount(User user);
}
