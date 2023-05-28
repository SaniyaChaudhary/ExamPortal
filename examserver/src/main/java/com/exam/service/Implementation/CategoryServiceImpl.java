package com.exam.service.Implementation;

import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepo;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Category addCategory(Category category) {

        return this.categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepo.findAll()) ;
    }

    @Override
    public Category getCategory(Long caterogyId) {
        return this.categoryRepo.findById(caterogyId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=new Category();
        category.setCid(categoryId);
        this.categoryRepo.delete(category);


    }
}
