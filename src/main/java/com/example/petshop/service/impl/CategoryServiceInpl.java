package com.example.petshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.Category;
import com.example.petshop.exception.NotFoundException;
import com.example.petshop.payload.request.CreateCategoryRequest;
import com.example.petshop.repository.CategoryRepository;
import com.example.petshop.service.CategoryService;

@Service
public class CategoryServiceInpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findALl() {
        // TODO Auto-generated method stub
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
        return category;
    }

    @Override
    public Category save(CreateCategoryRequest request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setEnabled(true);
        
        // TODO Auto-generated method stub
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id,CreateCategoryRequest request) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void enabled(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
        if(category.isEnabled()){
            category.setEnabled(false);
        } else{
            category.setEnabled(true);
        }
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getListEnabled() {
        // TODO Auto-generated method stub
        return categoryRepository.findALLByEnabled();
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
        categoryRepository.delete(category);
    }
    
}
