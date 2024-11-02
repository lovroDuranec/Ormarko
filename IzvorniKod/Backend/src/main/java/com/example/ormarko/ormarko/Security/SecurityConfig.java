package com.example.ormarko.ormarko.Security;

import com.example.ormarko.ormarko.Service.MyRegisteredUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private final MyRegisteredUserService registeredUserService;

    @Bean
    public UserDetailsService userDetailsService(){
        return registeredUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(registeredUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(l -> l.defaultSuccessUrl("/user").loginPage("/login").permitAll())
                .authorizeHttpRequests(registry ->{
                    registry.requestMatchers("/",  "home", "/advertiser/**", "/signup", "/register/**").permitAll();
                    registry.requestMatchers("/user/**").authenticated();
                    registry.requestMatchers("/profile").authenticated();
                    registry.anyRequest().authenticated();
                })
                .logout(l -> l.logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
