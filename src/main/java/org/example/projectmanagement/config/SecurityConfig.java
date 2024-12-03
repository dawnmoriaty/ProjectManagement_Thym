package org.example.projectmanagement.config;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home/**", "/auth/login", "/auth/register").permitAll()//cho phép các trang thông dung được hiển thij
                        .requestMatchers("/super_admin/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")   // Chỉ người dùng có role "ADMIN" mới có quyền truy cập
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")// Chỉ người dùng có role "USER" mới có quyền truy cập
                        .requestMatchers(("/employee/**")).hasRole("EMPLOYEE")
                        .anyRequest().authenticated()  // Các request khác yêu cầu phải đăng nhập
                )
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .usernameParameter("userName")
                        .passwordParameter("password")
                        .failureUrl("/auth/login?err"))  // Cấu hình login form
                ;

        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
