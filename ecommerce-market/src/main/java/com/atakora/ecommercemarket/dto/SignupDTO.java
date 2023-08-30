package com.atakora.ecommercemarket.dto;

import com.atakora.ecommercemarket.enums.UserRole;
import lombok.Data;

@Data
public class SignupDTO {
    private String name;
    private String email;
    private  String password;
    private UserRole userRole;
}
