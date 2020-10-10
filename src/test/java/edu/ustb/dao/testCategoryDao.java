package edu.ustb.dao;

import edu.ustb.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testCategoryDao {

    @Autowired
    private ICategoryDao categoryDao;

    @Test
    public void testFindAll() {
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
