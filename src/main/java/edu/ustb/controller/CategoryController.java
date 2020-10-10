package edu.ustb.controller;

import edu.ustb.domain.Category;
import edu.ustb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Category> findAll() {
        return categoryService.findAll();
    }

}
