package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Dòng này giúp bạn in ra mã Hash chuẩn của mật khẩu "123456"
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("=========================================");
		System.out.println("Mã BCrypt mới của bạn là: " + encoder.encode("123456"));
		System.out.println("=========================================");
		
		SpringApplication.run(DemoApplication.class, args);
	}

}