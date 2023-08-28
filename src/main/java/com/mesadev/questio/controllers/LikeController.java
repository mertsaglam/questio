package com.mesadev.questio.controllers;

import com.mesadev.questio.entities.Like;
import com.mesadev.questio.requests.LikeCreateRequest;
import com.mesadev.questio.responses.LikeResponse;
import com.mesadev.questio.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return likeService.getAllLikes(postId,userId);
    }
    @PostMapping
    public Like createSingleLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createSingleLike(likeCreateRequest);
    }
    @GetMapping("/{likeId}")
    public Optional<Like> getSingleLike(@PathVariable Long likeId){
        return likeService.getSingleLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteSingleLike(@PathVariable Long likeId){
        likeService.deleteSingleLike(likeId);
    }
}
