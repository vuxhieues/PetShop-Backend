package com.example.petshop.payload.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    
    private String username;

    private String email;

    private String country;
    
    private String address;

    private Date birtday;

    private String phone;

    
}
