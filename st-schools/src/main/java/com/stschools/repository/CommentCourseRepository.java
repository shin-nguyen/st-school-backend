package com.stschools.repository;

import com.stschools.entity.CommentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentCourseRepository extends JpaRepository<CommentCourse, Long> {
    List<CommentCourse> findAllByOrderByIdAsc();
    List<CommentCourse> findAllByCourseId(Long id);
    CommentCourse findCommentCourseById(Long id);
}
