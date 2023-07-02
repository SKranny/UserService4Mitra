package com.example.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfig {
    @Bean
    @Primary
    public HttpSecurity httpSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> {
                    conf.antMatchers("/api/v1/user/**").authenticated();
                    conf.antMatchers("/api/v1/user/editor/**").hasRole("admin");
                    conf.antMatchers("/v3/api-user/**").permitAll();
                    conf.antMatchers("/docs/**").permitAll();
                    conf.anyRequest().authenticated();
                });
    }
}
