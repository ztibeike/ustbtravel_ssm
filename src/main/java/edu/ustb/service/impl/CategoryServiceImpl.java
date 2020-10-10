package edu.ustb.service.impl;

import edu.ustb.dao.ICategoryDao;
import edu.ustb.domain.Category;
import edu.ustb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
