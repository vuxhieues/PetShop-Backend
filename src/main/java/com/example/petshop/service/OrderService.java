package com.example.petshop.service;

import java.util.List;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderDetail;
import com.example.petshop.payload.request.CreateOrderRequest;

public interface OrderService {
    

    List<Order> getListOrder();

    Order createOrder(CreateOrderRequest request);

    List<OrderDetail> getListOrderDetail(long id);

    List<Order> getListOrderByUser(long id);
}
