package com.kubrabayrakci.AstronomyBlog.config;

import com.kubrabayrakci.AstronomyBlog.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                        .antMatchers("/cms/**").hasAnyAuthority("ADMIN")
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                        .and().formLogin().loginPage("/login").permitAll()
                        .and().logout().permitAll()
                        .and().exceptionHandling().accessDeniedPage("/403");

        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
