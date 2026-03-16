package com.ecommerce.sb_ecom.security;

import com.ecommerce.sb_ecom.security.jwt.AuthEntryPointJwt;
import com.ecommerce.sb_ecom.security.jwt.AuthTokenFilter;
import com.ecommerce.sb_ecom.security.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // JWT Filter Bean
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // Authentication Provider
    // Responsible for validating username and password
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        // ✅ Pass UserDetailsService via constructor, PasswordEncoder via setter
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);

        // Uses BCrypt to compare encrypted passwords
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Authentication Manager
    // Used to perform authentication during login
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {

        return authConfig.getAuthenticationManager();
    }

    // Security Filter Chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(unauthorizedHandler))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Auth APIs
                        .requestMatchers("/api/auth/**").permitAll()

                        // Swagger
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()

                        // Public APIs
                        .requestMatchers("/api/public/**").permitAll()

                        // Admin APIs
                        .requestMatchers("/api/admin/**").permitAll()

                        // Test APIs
                        .requestMatchers("/api/test/**").permitAll()

                        // Images
                        .requestMatchers("/api/images/**").permitAll()

                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Ignore swagger resources
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web -> web.ignoring().requestMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        ));
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}