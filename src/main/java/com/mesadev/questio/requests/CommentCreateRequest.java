package com.mesadev.questio.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long id;
    private Long postId;
    private String text;
    private Long userId;
}
