package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Cấu hình mã hóa mật khẩu BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Cấu hình phân quyền chi tiết
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Quy định 1: Các thao tác Thêm, Sửa, Xóa phải là ADMIN
                // Lưu ý: Đã sửa thành /products/... để khớp với ProductController của bạn
                .requestMatchers("/products/add/**", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")
                
                // Quy định 2: Trang danh sách sản phẩm thì cả USER và ADMIN đều vào được
                .requestMatchers("/products").hasAnyRole("USER", "ADMIN")
                
                // Quy định 3: Các tài nguyên tĩnh (css, js, images) cho phép truy cập tự do
                .requestMatchers("/css/**", "/js/**", "/images/**", "/uploads/**").permitAll()

                // Quy định 4: Tất cả các yêu cầu khác đều phải đăng nhập
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Đăng nhập thành công thì chuyển về trang danh sách sản phẩm
                .defaultSuccessUrl("/products", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            // Tắt CSRF để tránh lỗi khi thực hiện các method POST/PUT/DELETE từ Form
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}