package com.mesadev.questio.repos;

import com.mesadev.questio.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Comment> findByUserId(Long aLong);

    List<Comment> findByPostId(Long aLong);
}
