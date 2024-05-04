package com.example.petshop.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    
    private String lastname;

    private String email;

    private String address;

    private String phone;

    private String note;

    private long totalPrice;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;   
    
    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private Set<OrderDetail> orderDetails;
}
