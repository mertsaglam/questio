package com.mesadev.questio.services;

import com.mesadev.questio.entities.Comment;
import com.mesadev.questio.entities.Post;
import com.mesadev.questio.entities.User;
import com.mesadev.questio.repos.CommentRepository;
import com.mesadev.questio.requests.CommentCreateRequest;
import com.mesadev.questio.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        //both exists
        if (userId.isPresent() && postId.isPresent()) {

            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        //only userId
        else if (userId.isPresent() )  {
            return commentRepository.findByUserId(userId.get());

        }
        //only postId
        else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }
        //no param
        else{
            return commentRepository.findAll();
        }
    }


    public Optional<Comment> getSingleCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment createSingleComment(CommentCreateRequest newCommentRequest) {

        User user =userService.getSingleUser(newCommentRequest.getUserId());
        Post post = postService.getSinglePostById(newCommentRequest.getPostId());
        if(user == null || post == null){
            return null;
        }
        else{
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentRequest.getId());
            commentToSave.setText(newCommentRequest.getText());
            commentToSave.setUser(user);
            commentToSave.setPost(post);

            return commentRepository.save(commentToSave);
        }
    }

    public Comment updateSingleCommentById(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment toUpdate = comment.get();
            toUpdate.setText(updateComment.getText());
            commentRepository.save(toUpdate);
            return toUpdate;
        }
        else{
            return null;
        }
    }

    public void deleteSingleCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
