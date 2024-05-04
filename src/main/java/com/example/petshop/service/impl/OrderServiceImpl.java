package com.example.petshop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderDetail;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.exception.NotFoundException;
import com.example.petshop.payload.request.CreateOrderDetailRequest;
import com.example.petshop.payload.request.CreateOrderRequest;
import com.example.petshop.repository.OrderDetailRepository;
import com.example.petshop.repository.OrderRepository;
import com.example.petshop.repository.ProductRepository;
import com.example.petshop.repository.UserRepository;
import com.example.petshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getListOrder() {
        // TODO Auto-generated method stub
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Order order = new Order();
        String username = request.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not found user with username:" + request.getUsername()));
        order.setFirstname(request.getFirstname());
        order.setLastname(request.getLastname());
        order.setEmail(request.getEmail());
        order.setAddress(request.getAddress());
        order.setPhone(request.getPhone());
        order.setUser(user);
        order.setNote(request.getNote());
        orderRepository.save(order);
        long totalPrice = 0;
        for(CreateOrderDetailRequest od : request.getOrderDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(od.getName());
            orderDetail.setPrice(od.getPrice());
            orderDetail.setQty(od.getQty());
            orderDetail.setSubTotal(od.getPrice() * od.getQty());
            orderDetail.setOrder(order);
            totalPrice += orderDetail.getSubTotal();
            orderDetailRepository.save(orderDetail);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<OrderDetail> getListOrderDetail(long id) {
        // TODO Auto-generated method stub
        List<OrderDetail> orderDetails = orderDetailRepository.getListOrderDetails(id);
        return orderDetails;
    }

    @Override
    public List<Order> getListOrderByUser(long id) {
        // TODO Auto-generated method stub
        List<Order> orders = orderRepository.getOrderByUser(id);
        return orders;
    }


    
    
}
