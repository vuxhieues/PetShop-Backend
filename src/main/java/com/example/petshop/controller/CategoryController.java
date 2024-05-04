package com.example.petshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petshop.entity.Category;
import com.example.petshop.payload.request.CreateCategoryRequest;
import com.example.petshop.payload.response.MessageResponse;
import com.example.petshop.service.CategoryService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getListCategory(){
        List<Category> categories = categoryService.findALl();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/enabled")
    public ResponseEntity<?> getListCategoryEnabled(){
        List<Category> categories = categoryService.getListEnabled();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable long id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.save(request);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.update(id, request);
        return ResponseEntity.ok(category);
    }


    @PutMapping("/enabled/{id}")
    public ResponseEntity<?> enabled(@PathVariable long id){
        categoryService.enabled(id);
        return ResponseEntity.ok(new MessageResponse("Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công"));
    }

}
