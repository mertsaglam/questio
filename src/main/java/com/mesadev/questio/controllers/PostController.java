package com.mesadev.questio.controllers;

import com.mesadev.questio.entities.Post;
import com.mesadev.questio.requests.PostCreateRequest;
import com.mesadev.questio.requests.PostUpdateRequest;
import com.mesadev.questio.responses.PostResponse;
import com.mesadev.questio.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }
    @PostMapping
    public Post createSinglePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createSinglePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public PostResponse getSinglePost(@PathVariable Long postId){
        return postService.getSinglePostByIdWithLikes(postId);
    }

    @PutMapping("/{postId}")
    public Post updateSinglePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postToUpdate ){
        return postService.updateSinglePostById(postId, postToUpdate);
    }
    @DeleteMapping("/{postId}")
    public void deleteSinglePost(@PathVariable Long postId){
        postService.deleteSinglePostById(postId);
    }

}
