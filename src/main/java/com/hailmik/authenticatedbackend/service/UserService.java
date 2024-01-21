package com.hailmik.authenticatedbackend.service;


import com.hailmik.authenticatedbackend.model.ApplicationUser;
import com.hailmik.authenticatedbackend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        if(!username.equals("Ethan")) {
            throw new UsernameNotFoundException("Not Ethan");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));
        return new ApplicationUser("Ethan", encoder.encode("password"), roles);
    }
}
