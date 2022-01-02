package com.stschools.repository;

import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentBlog, Long> {
    List<CommentBlog> findAllByOrderByIdAsc();
    List<CommentBlog> findAllByBlogId(Long id);
    CommentBlog findCommentBlogById(Long id);
    @Modifying
    @Query("update CommentBlog b set b.content = ?2 where b.id = ?1")
    void updateBlogStatus(Long id, String content);
}
