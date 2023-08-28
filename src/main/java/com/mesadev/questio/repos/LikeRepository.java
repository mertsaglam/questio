package com.mesadev.questio.repos;

import com.mesadev.questio.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPostIdAndUserId(Optional<Long> postId, Optional<Long> userId);

    List<Like> findByPostId(Optional<Long> postId);

    List<Like> findByUserId(Optional<Long> postId);
}
