package com.study.board.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class PasswordConfig { // 파일명과 클래스명 일치

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ★ 이 부분이 없으면 로그인 화면이 안 뜨고 에러가 납니다!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 보안 검사 끄기 (실습용)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // "모든 곳 다 들어가게 해줘!"
                );
        return http.build();
    }
}