package com.mesadev.questio.services;

import com.mesadev.questio.entities.Like;
import com.mesadev.questio.entities.Post;
import com.mesadev.questio.entities.User;
import com.mesadev.questio.repos.LikeRepository;
import com.mesadev.questio.requests.LikeCreateRequest;
import com.mesadev.questio.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(UserService userService,LikeRepository likeRepository,PostService postService){
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        List<Like> list;
        if(postId.isPresent() && userId.isPresent()){
            list =  likeRepository.findByPostIdAndUserId(postId,userId);
        }
        else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId);
        }
        else if (userId.isPresent()) {
            list =  likeRepository.findByUserId(postId);
        }
        else {
            list = likeRepository.findAll();
        }
        return list.stream().map(like ->new LikeResponse(like)).collect(Collectors.toList());

    }

    public Optional<Like> getSingleLikeById(Long likeId) {
        return likeRepository.findById(likeId);
    }

    public Like createSingleLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getSingleUser(likeCreateRequest.getUserId());
        Post post = postService.getSinglePostById(likeCreateRequest.getPostId());

        if(user == null || post == null){
            return null;
        }
        else {
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);

        }

    }

    public void deleteSingleLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
