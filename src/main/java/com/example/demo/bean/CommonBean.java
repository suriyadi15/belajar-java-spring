package com.example.demo.bean;

import com.example.demo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBean {

    @Bean
    public User theUser() {
        User user = new User();
        user.setName("Mark");

        return user;
    }
}
