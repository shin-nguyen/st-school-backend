package com.stschools.repository;

import com.stschools.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findBlogById(Long id);
    List<Blog> findAllByOrderByIdAsc();
    List<Blog> findByIdIn(List<Long> blogsId);
    Long countById(Long id);
}
