package com.mesadev.questio.controllers;

import com.mesadev.questio.entities.User;
import com.mesadev.questio.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveSingleUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getSingleUserById(@PathVariable Long userId){
        return userService.getSingleUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateSingleUser(@PathVariable Long userId, @RequestBody User newUser){
        return userService.updateSingleUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteSingleUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
