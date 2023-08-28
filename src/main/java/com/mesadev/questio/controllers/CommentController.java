package com.mesadev.questio.controllers;

import com.mesadev.questio.entities.Comment;
import com.mesadev.questio.entities.Post;
import com.mesadev.questio.requests.CommentCreateRequest;
import com.mesadev.questio.requests.CommentUpdateRequest;
import com.mesadev.questio.requests.PostUpdateRequest;
import com.mesadev.questio.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId,postId);
    }
    @PostMapping
    public Comment createSingleComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createSingleComment(commentCreateRequest);
    }
    @PutMapping("/{commentId}")
    public Comment updateSingleComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentToUpdate){
        return commentService.updateSingleCommentById(commentId, commentToUpdate);
    }
    @DeleteMapping("/{commentId}")
    public void deleteSingleComment(@PathVariable Long commentId){
        commentService.deleteSingleCommentById(commentId);
    }

    @GetMapping("/{commentId}")
    public Optional<Comment> getSingleComment(@PathVariable Long commentId){
        return commentService.getSingleCommentById(commentId);
    }


}
