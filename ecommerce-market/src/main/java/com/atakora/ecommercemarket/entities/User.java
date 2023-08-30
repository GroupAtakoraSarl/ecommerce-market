package com.atakora.ecommercemarket.entities;

import com.atakora.ecommercemarket.dto.UserDTO;
import com.atakora.ecommercemarket.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
    private byte[] img;
    public String Password;


    //public UserDTO mapUserToUserDTO() {
   //     return  new UserDTO(id,name,email,userRole);
    // }
}
