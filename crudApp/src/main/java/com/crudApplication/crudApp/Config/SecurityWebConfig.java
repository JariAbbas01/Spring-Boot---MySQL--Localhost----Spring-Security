package com.crudApplication.crudApp.Config;

import com.crudApplication.crudApp.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig {


    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private EncoderConfig encoderConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                (auth)->auth
                        .requestMatchers("/journal/**","/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().permitAll()
        ).csrf(x-> x.disable()).httpBasic(Customizer.withDefaults()).sessionManagement(x->x.disable());
        return httpSecurity.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(encoderConfig.passwordEncoder());
    }

}
