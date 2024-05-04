package com.example.petshop.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    @Email(message = "Email không đúng định dạng")
    private String email;

    private String password;

    private Set<String> role;

}
