package com.atakora.ecommercemarket.controller;

import com.atakora.ecommercemarket.dto.AuthenticationRequest;
import com.atakora.ecommercemarket.dto.AuthenticationResponse;
import com.atakora.ecommercemarket.entities.User;
import com.atakora.ecommercemarket.repository.UserRepository;
import com.atakora.ecommercemarket.service.user.UserService;
import com.atakora.ecommercemarket.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping ("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IDException, JSONException, ServletException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"user is not activate");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
