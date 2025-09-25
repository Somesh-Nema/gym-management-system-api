package com.gym.gym_management_system.services;

import com.gym.gym_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // "username" in this context is the email
        com.gym.gym_management_system.entities.User customUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Create a GrantedAuthority from our user's role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customUser.getRole().name());

        // Convert our custom User entity into Spring Security's User object
        return new org.springframework.security.core.userdetails.User(
                customUser.getEmail(),
                customUser.getPassword(),
                Collections.singletonList(authority)
        );
    }
}