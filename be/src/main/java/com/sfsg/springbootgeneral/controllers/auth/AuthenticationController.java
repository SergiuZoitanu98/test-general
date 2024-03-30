package com.sfsg.springbootgeneral.controllers.auth;

import com.sfsg.springbootgeneral.dto.AuthenticationRequest;
import com.sfsg.springbootgeneral.dto.AuthenticationResponse;
import com.sfsg.springbootgeneral.dto.RegisterRequest;
import com.sfsg.springbootgeneral.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
    return ResponseEntity.ok(authenticationService.register(request));
    }




}
