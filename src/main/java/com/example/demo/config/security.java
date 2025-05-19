package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.config.jwt.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class Security {

        private final JwtAuthFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .csrf(csrf -> csrf.disable()) // ✅ Tắt CSRF (Cross-Site Request Forgery) vì chúng ta
                                .authorizeRequests(requests -> requests
                                                .requestMatchers("/**").permitAll()// ✅ Cho phép tất cả
                                                .anyRequest().authenticated()) // Các endpoint còn lại yêu cầu xác thực
                                // khi đăng nhập
                                // .oauth2Login(oauth2 -> oauth2
                                // .loginPage("/oauth2/authorization/google")
                                // .defaultSuccessUrl("/exam/home", true) // ✅ Đường dẫn đến trang thành
                                // .failureUrl("/auth/login")) // ✅ Đường dẫn đến trang thất bại sau

                                .exceptionHandling(e -> e
                                                .accessDeniedHandler(customAccessDeniedHandler)
                                                .authenticationEntryPoint(customAuthenticationEntryPoint)) // ✅ xử lý
                                // khi token
                                // thiếu/sai
                                .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .cors();
                httpSecurity.headers(headers -> headers.frameOptions().sameOrigin());
                return httpSecurity.build();
        }

        @Bean
        public CorsFilter corsFilter() {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowCredentials(true); // Allow credentials (cookies, authentication)
                corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000", "*")); // Specify
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allow specific HTTP
                corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); // Specify allowed headers
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", corsConfig); // Apply CORS configuration to all endpoints

                return new CorsFilter(source);
        }
}
