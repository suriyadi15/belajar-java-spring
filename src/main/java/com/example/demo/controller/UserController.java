package com.example.demo.controller;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserRequest data){
        User user = new User();
        user.setEmail(data.getEmail());
        user.setName(data.getName());

        userRepo.save(user);

        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@RequestBody UpdateUserRequest data, @PathVariable("id") Integer id){
        User user = finUserByIdOrThrow(id);

        user.setEmail(data.getEmail());
        user.setName(data.getName());

        userRepo.save(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(finUserByIdOrThrow(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        userRepo.delete(finUserByIdOrThrow(id));

        return ResponseEntity.noContent()
                .build();
    }

    private User finUserByIdOrThrow(Integer id){
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty()) throw new RuntimeException("user with id " + id + " not found");

        return user.get();
    }
}
