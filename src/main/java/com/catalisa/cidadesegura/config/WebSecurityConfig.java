package com.catalisa.cidadesegura.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.httpBasic().and().authorizeHttpRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers(HttpMethod.GET,"/postagens").permitAll()
                .antMatchers(HttpMethod.GET,"/postagens/{idPostagem}").permitAll()
                .antMatchers(HttpMethod.POST,"/postagens").permitAll()
                .antMatchers(HttpMethod.DELETE,"/postagens/{idPostagem}").permitAll()
                .anyRequest().authenticated().and().csrf().disable();

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

}

