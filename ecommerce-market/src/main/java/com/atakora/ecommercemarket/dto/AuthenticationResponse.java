package com.atakora.ecommercemarket.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
     private String jwtToken;

    public AuthenticationResponse(String jwt) {
    }
}

