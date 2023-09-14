package com.mesadev.questio.services;

import com.mesadev.questio.entities.User;
import com.mesadev.questio.repos.CommentRepository;
import com.mesadev.questio.repos.LikeRepository;
import com.mesadev.questio.repos.PostRepository;
import com.mesadev.questio.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {

        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveSingleUser(User newUser) {
        return userRepository.save(newUser);
    }


    public User getSingleUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateSingleUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getSingleUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if (postIds.isEmpty())
            return null;

        List<Object> activity = new ArrayList<>();
        activity.addAll(commentRepository.findUserCommentsByPostId(postIds));
        activity.addAll(likeRepository.findUserLikesByPostId(postIds));
        return activity;

    }

    public User updateUserAvatar(Long userId, Integer avatarId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setAvatar(avatarId);
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }
}