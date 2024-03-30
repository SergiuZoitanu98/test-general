package com.sfsg.springbootgeneral.services;

import com.sfsg.springbootgeneral.dto.AuthenticationRequest;
import com.sfsg.springbootgeneral.dto.AuthenticationResponse;
import com.sfsg.springbootgeneral.dto.RegisterRequest;
import com.sfsg.springbootgeneral.entities.user.Role;
import com.sfsg.springbootgeneral.entities.user.User;
import com.sfsg.springbootgeneral.repositories.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setToken(jwtToken);
    return  authenticationResponse;
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
