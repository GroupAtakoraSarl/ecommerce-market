package com.atakora.ecommercemarket.dto;

import com.atakora.ecommercemarket.enums.UserRole;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
    //private byte[] img;
   // private MultipartFile img;
}

