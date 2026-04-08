package com.myorganisation.vibehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
       httpSecurity
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(

                       auth -> auth
                       .requestMatchers("/test").permitAll()
                       .anyRequest().authenticated()
               )
               .httpBasic(Customizer.withDefaults());


       return httpSecurity.build();
    }
}
