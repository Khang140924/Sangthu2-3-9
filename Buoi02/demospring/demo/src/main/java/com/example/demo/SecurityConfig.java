package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Quan trọng: Tắt CSRF để Postman gửi được lệnh POST/PUT/DELETE
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Yêu cầu đăng nhập với mọi link
            )
            .httpBasic(withDefaults()); // Sử dụng chế độ đăng nhập Basic Auth (user/123456)
        
        return http.build();
    }
}