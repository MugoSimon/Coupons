package com.mugosimon.Coupons.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WebSecurityConfigTest {

    private WebSecurityConfig webSecurityConfigUnderTest;

    @BeforeEach
    void setUp() {
        webSecurityConfigUnderTest = new WebSecurityConfig();
    }

    @Test
    void testPasswordEncoder() {
        // Setup
        // Run the test
        final BCryptPasswordEncoder result = webSecurityConfigUnderTest.passwordEncoder();

        // Verify the results
    }

    @Test
    void testFilterChain() throws Exception {
        // Setup
        final HttpSecurity httpSecurity = new HttpSecurity(null, new AuthenticationManagerBuilder(null),
                Map.ofEntries(Map.entry(String.class, "value")));

        // Run the test
        final SecurityFilterChain result = webSecurityConfigUnderTest.filterChain(httpSecurity);

        // Verify the results
    }

    @Test
    void testFilterChain_ThrowsException() {
        // Setup
        final HttpSecurity httpSecurity = new HttpSecurity(null, new AuthenticationManagerBuilder(null),
                Map.ofEntries(Map.entry(String.class, "value")));

        // Run the test
        assertThatThrownBy(() -> webSecurityConfigUnderTest.filterChain(httpSecurity)).isInstanceOf(Exception.class);
    }
}
