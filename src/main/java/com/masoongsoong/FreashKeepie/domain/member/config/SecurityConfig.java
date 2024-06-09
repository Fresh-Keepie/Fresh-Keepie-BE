
package com.masoongsoong.FreashKeepie.domain.member.config;//package com.masoongsoong.FreashKeepie.domain.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;



@Configuration
@EnableWebSecurity

public class SecurityConfig{

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf((csrf) -> csrf.disable())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                //        .anyRequest().authenticated()) // 다른 모든 요청은 인증 필요


                // .csrf((csrf) -> csrf
                //         .ignoringRequestMatchers("/h2-console/**","/board/**","/user/signup", "/user/login","/product/","/product/fridge")) // /h2-console 경로에 대해 CSRF 보호 비활성화


                        .requestMatchers("/resources/**","/mypage/**", "/board/**","/user/signup", "/user/login", "/product/fridge/**", "/ingredient/create", "/product", "/fridge/**","product/list/{fridgeId}","/product/{userId}/{ingredientsId}", "/ingredient/update/{userId}/{ingredientsDetailId}").permitAll()
                        .anyRequest().permitAll()) // 다른 모든 요청은 인증 필요g
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/user/signup", "/user/login", "/product/**", "/product/fridge", "/ingredient/create","/product",  "/fridge/list","/product/list","product/list/{fridgeId}","/product/{userId}/{ingredientsId}", "/update/{userId}/{ingredientsDetailId}", "/ingredient/update/{userId}/{ingredientsDetailId}")) // /h2-console 경로에 대해 CSRF 보호 비활성화

                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        ));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

