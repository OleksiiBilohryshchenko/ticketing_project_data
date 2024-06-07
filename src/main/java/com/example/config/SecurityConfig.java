package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//
//        List<UserDetails> userList = new ArrayList<>();
//
//        userList.add(
//                new User("Mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(
//                new User("Alex", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));
//
//
//        return new InMemoryUserDetailsManager(userList);
//
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/**").hasRole("ADMIN") ROLE_ADMIN -> must be same in DB, hasRole adding prefix
                        .requestMatchers("/user/**").hasAuthority("Admin")
                        .requestMatchers("/project/**").hasAuthority("Manager")
                        .requestMatchers("/task/employee/**").hasAuthority("Employee")
                        .requestMatchers("/task/**").hasAuthority("Manager")
//                        .requestMatchers("/task/**").hasAnyRole("MANAGER","ADMIN")
//                        .requestMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE") ROLE_EMPLOYEE not DB scenario, for manual creation
                        .requestMatchers(
                                "/",
                                "/login",
                                "/fragments/**",
                                "/assets/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/welcome")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout ->
                logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL to trigger logout
                        .logoutSuccessUrl("/login?logout=true") // Redirect after successful logout
                        .permitAll() // Allow all users to see the logout page
        );

                return http.build();

    }



}

