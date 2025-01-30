package com.bigeventbackend.service;

import com.bigeventbackend.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    // update category
    void update(Category category);

    void deleteById(Integer id);
}
