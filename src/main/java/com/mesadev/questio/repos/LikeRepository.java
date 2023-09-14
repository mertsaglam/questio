package com.mesadev.questio.repos;

import com.mesadev.questio.entities.Comment;
import com.mesadev.questio.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPostIdAndUserId(Optional<Long> postId, Optional<Long> userId);

    List<Like> findByPostId(Optional<Long> postId);

    List<Like> findByUserId(Optional<Long> postId);

    @Query(value = "Select 'liked', l.post_id, u.avatar,u.user_name " +
            "from p_like l left join user u on u.id = l.user_id " +
            "where l.post_id in :postIds limit 5" , nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds")List<Long> postIds);
}
