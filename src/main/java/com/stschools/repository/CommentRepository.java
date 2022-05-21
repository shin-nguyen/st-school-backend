package com.stschools.repository;

import com.stschools.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBlogId(Long id);
    List<Comment> findAllByCourseId(Long id);
    Comment findCommentById(Long id);
}
