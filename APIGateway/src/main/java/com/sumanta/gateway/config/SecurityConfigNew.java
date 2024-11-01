package com.sumanta.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfigNew {
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withUsername("admin123")
                .password("admin123")
                .roles("ADMIN","USERS")
                .build();
        UserDetails user2 = User
                .withUsername("sumanta")
                .password("sumanta")
                .roles("ADMIN")
                .build();


        var inMemoryuserDetailService= new InMemoryUserDetailsManager();
       return inMemoryuserDetailService;
    }
}
