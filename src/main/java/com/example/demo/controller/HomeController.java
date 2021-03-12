package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private User user;

    public HomeController(User user){
        this.user = user;
    }

    @GetMapping("/")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Hello Spring Boot");
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(){
        return ResponseEntity.ok("Hello " + user.getName());
    }
}
