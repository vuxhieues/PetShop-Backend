package com.example.petshop.service;

import java.util.List;

import com.example.petshop.entity.Category;
import com.example.petshop.payload.request.CreateCategoryRequest;

public interface CategoryService {
    
    List<Category> findALl();

    Category findById(long id);

    Category save(CreateCategoryRequest request);

    Category update(long id,CreateCategoryRequest request);

    void enabled(long id);

    List<Category> getListEnabled();

    void deleteById(long id);

}
