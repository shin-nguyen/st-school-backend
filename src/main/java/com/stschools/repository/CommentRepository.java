package com.stschools.repository;

import com.stschools.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByIdAsc();
    List<Comment> findAllByBlogId(Long id);
    Comment findCommentBlogById(Long id);
    List<Comment> findByCourseId(Long id);
    List<Comment> findByBlogId(Long id);
}
