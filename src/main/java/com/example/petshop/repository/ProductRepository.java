package com.example.petshop.repository;

import java.util.List;

import com.example.petshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "Select * from Product where id != :id order by id desc limit 5",nativeQuery = true)
    List<Product> getListNewest(long id);
    
    @Query(value ="Select * from Product where category_id = :id AND enabled = 1 and id != :productId order by id desc limit 8",nativeQuery = true)
    List<Product> findByCategoryId(long id,long productId);

    @Query(value ="Select * from Product where category_id = :id AND enabled = 1 order by id desc",nativeQuery = true)
    List<Product> getListProductCategory(long id);

    @Query("Select p from Product p where p.enabled = true")
    List<Product> findALLByEnabled();
}
