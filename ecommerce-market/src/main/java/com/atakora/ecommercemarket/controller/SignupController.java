package com.atakora.ecommercemarket.controller;

import com.atakora.ecommercemarket.dto.SignupDTO;
import com.atakora.ecommercemarket.dto.UserDTO;
import com.atakora.ecommercemarket.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SignupController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO){
        if (userService.hasUserWithEmail(signupDTO.getEmail())){
            return new ResponseEntity<>("user already exist",HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO createdUser = userService.createUser(signupDTO);
        if (createdUser == null){
            return  new ResponseEntity<>("User not found. Come again later", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}

