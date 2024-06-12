package com.mugosimon.Coupons.service;

import com.mugosimon.Coupons.model.User;
import com.mugosimon.Coupons.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Log the start of the loadUserByUsername method
        log.info("Attempting to load user details for username: {}", username);

        // Try to find the user by email
        Optional<User> userOptional = userRepository.findByEmail(username);

        // Check if the user was found
        if (userOptional.isEmpty()) {
            // Log the user not found scenario
            log.warn("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Get the user object
        User user = userOptional.get();

        // Log the successful user retrieval
        log.info("User found: {}", user);

        // Create and return the UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

}
