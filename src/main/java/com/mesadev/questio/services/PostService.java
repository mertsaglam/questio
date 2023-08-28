package com.mesadev.questio.services;

import com.mesadev.questio.entities.Like;
import com.mesadev.questio.entities.Post;
import com.mesadev.questio.entities.User;
import com.mesadev.questio.repos.PostRepository;
import com.mesadev.questio.requests.PostCreateRequest;
import com.mesadev.questio.requests.PostUpdateRequest;
import com.mesadev.questio.responses.LikeResponse;
import com.mesadev.questio.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;

    }
    @Autowired
    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        }
        list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes= likeService.getAllLikes(Optional.of(p.getId()),Optional.ofNullable(null));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }


    public Post getSinglePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createSinglePost(PostCreateRequest newPostRequest) {
        User user =userService.getSingleUser(newPostRequest.getUserId());
        if(user == null){
            return null;
        }
        else{
            Post postToSave = new Post();
            postToSave.setId(newPostRequest.getId());
            postToSave.setText(newPostRequest.getText());
            postToSave.setTitle(newPostRequest.getTitle());
            postToSave.setUser(user);
            return postRepository.save(postToSave);
        }
    }


    public Post updateSinglePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        else{
            return null;
        }

    }

    public void deleteSinglePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
