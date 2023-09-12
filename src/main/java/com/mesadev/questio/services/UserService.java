package com.mesadev.questio.services;

import com.mesadev.questio.entities.User;
import com.mesadev.questio.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveSingleUser(User newUser) {
        return userRepository.save(newUser);
    }



    public User getSingleUser(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User updateSingleUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getSingleUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
