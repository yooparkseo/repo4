package com.chami.config;

import com.chami.vo.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        // 개발 전 적용
                        .anyRequest().permitAll()
                        // 개발 후 적용
                        /*.requestMatchers("/", "/login", "/signup", "/error").permitAll() // 기본
                        .requestMatchers("/resources/**", "/css/**", "/images/**", "/js/**", "/sass/**", "/videos/**", "/webfonts/**", "/favicon.ico").permitAll() // 정적소스
                        .requestMatchers("/posts/**", "/api/v1/posts/**").permitAll() // post 요청
                        .requestMatchers("/posts/**", "/api/v1/posts/**").hasRole(Role.USER.getRoleWithoutPrefix()) // 유저만 접근
                        .requestMatchers("/admins/**", "/api/v1/admins/**").hasRole(Role.ADMIN.getRoleWithoutPrefix()) // 관리자만 접근
                        .anyRequest().authenticated()*/);
        return http.build();
    }
}
