package com.example.petshop.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {
    
    private String name;

    private long price;

    private int qty;

    private String subTotal;
}
