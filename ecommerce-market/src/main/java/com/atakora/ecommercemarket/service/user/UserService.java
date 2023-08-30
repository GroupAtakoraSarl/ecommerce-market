package com.atakora.ecommercemarket.service.user;

import com.atakora.ecommercemarket.dto.SignupDTO;
import com.atakora.ecommercemarket.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);
    boolean hasUserWithEmail(String email);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);
}
