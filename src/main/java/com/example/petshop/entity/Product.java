package com.example.petshop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    private long price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_image",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images = new HashSet<>();
    
    private boolean enabled;

}
