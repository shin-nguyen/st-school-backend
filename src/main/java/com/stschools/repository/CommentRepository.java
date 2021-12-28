package com.stschools.repository;

import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentBlog, Long> {
    List<CommentBlog> findAllByOrderByIdAsc();
    List<CommentBlog> findAllByBlogId(Long id);
    CommentBlog findCommentBlogById(Long id);
}
