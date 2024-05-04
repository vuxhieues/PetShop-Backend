package com.example.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    
    @Query(value ="Select * from order_detail where order_id = :id order by id",nativeQuery = true)
    List<OrderDetail> getListOrderDetails(long id);
}
