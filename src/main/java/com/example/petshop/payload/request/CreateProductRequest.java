package com.example.petshop.payload.request;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private long price;

    @NotNull
    private int quantity;

    @NotNull
    private long category;

    @NotNull
    private Set<Long> image;
}
