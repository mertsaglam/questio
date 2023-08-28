package com.mesadev.questio.responses;

import com.mesadev.questio.entities.Like;
import com.mesadev.questio.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponse> postLikes;

    public PostResponse(Post post ,List<LikeResponse> likes){
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUserName();
        this.title = post.getTitle();
        this.text = post.getText();
        this.postLikes = likes;
    }

}
