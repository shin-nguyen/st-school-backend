package com.stschools.repository;

import com.stschools.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByIdAsc();
    List<Comment> findAllByBlogId(Long id);
    Comment findCommentBlogById(Long id);

    List<Comment> findByBlogId(Long id);
    List<Comment> findByCourseId(Long id);
=======
public interface CommentRepository extends JpaRepository<CommentBlog, Long> {
    List<CommentBlog> findAllByOrderByIdAsc();
    List<CommentBlog> findAllByBlogId(Long id);
    CommentBlog findCommentBlogById(Long id);
    @Modifying
    @Query("update CommentBlog b set b.content = ?2 where b.id = ?1")
    void updateBlogStatus(Long id, String content);
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
}
