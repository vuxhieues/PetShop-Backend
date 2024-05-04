package com.example.petshop.payload.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    
    private String firstname;

    private String lastname;

    private String email;

    private String address;

    private String phone;

    private String note;

    private String username;

    private List<CreateOrderDetailRequest> orderDetails;
}
