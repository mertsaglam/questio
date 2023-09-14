package com.mesadev.questio.responses;

import com.mesadev.questio.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private int avatarId;
    private String userName;

    public UserResponse(User user){
        this.id = user.getId();
        this.avatarId = user.getAvatar();
        this.userName = user.getUserName();
    }
}
