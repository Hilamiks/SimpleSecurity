package com.hailmik.authenticatedbackend.service;

import com.hailmik.authenticatedbackend.dto.LoginResponseDTO;
import com.hailmik.authenticatedbackend.model.ApplicationUser;
import com.hailmik.authenticatedbackend.model.Role;
import com.hailmik.authenticatedbackend.repository.RoleRepository;
import com.hailmik.authenticatedbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password) {

        System.out.println("trying to register");
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(username, encodedPassword, authorities));

    }

    public LoginResponseDTO loginUser(String username, String password) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateJwt(auth);

        return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

    }

}