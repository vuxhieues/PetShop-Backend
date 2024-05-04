package com.example.petshop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.Category;
import com.example.petshop.entity.Image;
import com.example.petshop.entity.Product;
import com.example.petshop.exception.NotFoundException;
import com.example.petshop.payload.request.CreateProductRequest;
import com.example.petshop.repository.CategoryRepository;
import com.example.petshop.repository.ImageRepository;
import com.example.petshop.repository.ProductRepository;
import com.example.petshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Product> getList() {
        // TODO Auto-generated method stub
        return productRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Product> getListNewest(long id) {
        // TODO Auto-generated method stub
        return productRepository.getListNewest(id);
    }

    @Override
    public List<Product> getListProductByCategory(long id,long productId) {
        // TODO Auto-generated method stub
        return productRepository.findByCategoryId(id,productId);
    }

    @Override
    public Product findById(long id) {
        // TODO Auto-generated method stub
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return product;
    }

    @Override
    public Product save(CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setContent(request.getContent());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setEnabled(true);
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(()-> new NotFoundException("Category not found with id:" + request.getCategory()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId : request.getImage()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Image not found with id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        return productRepository.save(product);
    }

    @Override
    public Product update(long id, CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setContent(request.getContent());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(()-> new NotFoundException("Category not found with id:" + request.getCategory()));
        product.setCategory(category);
        Set<Image> images = new HashSet<>();
        for(long imageId : request.getImage()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Image not found with id:   " + imageId));
            images.add(image);
        }
        product.setImages(images);
        return productRepository.save(product);
    }

    @Override
    public void enabled(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        if(product.isEnabled()){
            product.setEnabled(false);
        }else{
            product.setEnabled(true);
        }
        productRepository.save(product);
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        product.getImages().remove(this);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getListProductCategory(long id) {
        // TODO Auto-generated method stub
        return productRepository.getListProductCategory(id);
    }
    
}
