package com.dsimdev.api.controller;

import com.dsimdev.api.model.UserModel;
import com.dsimdev.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        return userRepository.save(user);
    }
    @GetMapping("{id}")
    public UserModel findUserById(@PathVariable long id){
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public UserModel updateUser(@PathVariable long id, @RequestBody UserModel userDetails){
        UserModel updateUser = userRepository.findById(id).orElse(null);
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setAge(userDetails.getAge());
        updateUser.setCountry(userDetails.getCountry());
        userRepository.save(updateUser);

        return updateUser;

    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }



}
