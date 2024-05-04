package com.example.petshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
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

import com.example.petshop.entity.Product;
import com.example.petshop.payload.request.CreateProductRequest;
import com.example.petshop.payload.response.MessageResponse;
import com.example.petshop.service.ProductService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getList(){
        List<Product> listProduct = productService.getList();
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable long id){
        Product product = productService.findById(id);

        return ResponseEntity.ok(product);
    }


    @GetMapping("/category/{id}/{productId}")
    public ResponseEntity<?> getListByCategoryId(@PathVariable long id,@PathVariable long productId){
        List<Product> listProduct = productService.getListProductByCategory(id,productId);

        return ResponseEntity.ok(listProduct);
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<?> getListByCategoryId(@PathVariable long id){
        List<Product> listProduct = productService.getListProductCategory(id);

        return ResponseEntity.ok(listProduct);
    }


    @GetMapping("/newest/{id}")
    public ResponseEntity<?> getNewestProduct(@PathVariable long id){
        List<Product> listProducts = productService.getListNewest(id);
        return ResponseEntity.ok(listProducts);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateProductRequest request){
        Product product = productService.save(request);
        
        return  ResponseEntity.ok(product);
        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody CreateProductRequest request){
        Product product = productService.update(id, request);

        return  ResponseEntity.ok(product);
    }


    @PutMapping("/enabled/{id}")
    public ResponseEntity<?> enable(@PathVariable long id){
        productService.enabled(id);

        return ResponseEntity.ok(new MessageResponse("Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        productService.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("Xóa thành công"));

    }


}
