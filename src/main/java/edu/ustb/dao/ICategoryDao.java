package edu.ustb.dao;

import edu.ustb.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDao {

    List<Category> findAll();

}
