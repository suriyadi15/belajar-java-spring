package com.example.demo.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
}
