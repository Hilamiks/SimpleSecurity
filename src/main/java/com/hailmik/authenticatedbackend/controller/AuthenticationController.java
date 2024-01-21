package com.hailmik.authenticatedbackend.controller;

import com.hailmik.authenticatedbackend.dto.LoginResponseDTO;
import com.hailmik.authenticatedbackend.dto.RegistrationDTO;
import com.hailmik.authenticatedbackend.model.ApplicationUser;
import com.hailmik.authenticatedbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(
            @RequestBody RegistrationDTO registrationDTO
            ) {

        return authenticationService.registerUser(
                registrationDTO.getUsername(),
                registrationDTO.getPassword()
        );
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(
            @RequestBody RegistrationDTO body
    ) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
