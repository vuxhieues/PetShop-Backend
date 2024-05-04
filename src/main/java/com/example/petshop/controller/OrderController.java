package com.example.petshop.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderDetail;
import com.example.petshop.payload.request.CreateOrderRequest;
import com.example.petshop.service.OrderService;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/order")
@RestController
public class OrderController {
    
    
    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateOrderRequest request){
        Order order = orderService.createOrder(request);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/")
    public ResponseEntity<?> getListOrder(){
        List<Order> listOrders = orderService.getListOrder();

        return ResponseEntity.ok(listOrders);
    }

    @GetMapping("/orderDetail/{id}")
    public ResponseEntity<?> getListOrderDetail(@PathVariable long id){
        List<OrderDetail> listOrderDetail = orderService.getListOrderDetail(id);

        return ResponseEntity.ok(listOrderDetail);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOrderByUser(@PathVariable long id){
        List<Order> list = orderService.getListOrderByUser(id);
        return ResponseEntity.ok(list);
    }

}
