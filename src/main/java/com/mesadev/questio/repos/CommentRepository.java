package com.mesadev.questio.repos;

import com.mesadev.questio.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Comment> findByUserId(Long aLong);

    List<Comment> findByPostId(Long aLong);

    @Query(value = "Select 'commented on', c.post_id,u.avatar,u.user_name " +
            "from comment c left join user u on u.id = c.user_id " +
            "where c.post_id in :postIds limit 5" , nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds")List<Long> postIds);
}
