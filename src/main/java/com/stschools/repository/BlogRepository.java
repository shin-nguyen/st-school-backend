package com.stschools.repository;

import com.stschools.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findBlogById(Long id);
    List<Blog> findAllByOrderByIdAsc();
    List<Blog> findAllByUserId(Long userId);

    @Modifying
    @Query("update Blog b set b.view = b.view + 1 where b.id = ?1")
    void updateView(Long blogId);

    @Modifying
    @Query("update Blog b set b.status = ?2 where b.id = ?1")
    void updateBlogStatus(Long blogId, boolean status);
}
