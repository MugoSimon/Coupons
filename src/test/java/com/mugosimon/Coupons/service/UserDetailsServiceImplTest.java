package com.mugosimon.Coupons.service;

import com.mugosimon.Coupons.model.Role;
import com.mugosimon.Coupons.model.User;
import com.mugosimon.Coupons.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepo mockUserRepository;

    private UserDetailsServiceImpl userDetailsServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userDetailsServiceImplUnderTest = new UserDetailsServiceImpl(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername() {
        // Setup
        // Configure UserRepo.findByEmail(...).
        final User user1 = new User();
        user1.setId(0L);
        user1.setFirstName("firstName");
        user1.setEmail("email");
        user1.setPassword("password");
        final Role role = new Role();
        user1.setRoles(Set.of(role));
        final Optional<User> user = Optional.of(user1);
        when(mockUserRepository.findByEmail("username")).thenReturn(user);

        // Run the test
        final UserDetails result = userDetailsServiceImplUnderTest.loadUserByUsername("username");

        // Verify the results
    }

    @Test
    void testLoadUserByUsername_UserRepoReturnsAbsent() {
        // Setup
        when(mockUserRepository.findByEmail("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userDetailsServiceImplUnderTest.loadUserByUsername("username"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
