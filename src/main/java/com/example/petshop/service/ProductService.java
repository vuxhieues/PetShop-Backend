package com.example.petshop.service;

import java.util.List;

import com.example.petshop.entity.Product;
import com.example.petshop.payload.request.CreateProductRequest;

public interface ProductService {
    
    List<Product> getList();

    List<Product> getListNewest(long id);

    List<Product> getListProductByCategory(long id,long productId);

    List<Product> getListProductCategory(long id);

    Product findById(long id);

    Product save(CreateProductRequest request);

    Product update(long id,CreateProductRequest request);

    void enabled(long id);

    void deleteById(long id);
}
