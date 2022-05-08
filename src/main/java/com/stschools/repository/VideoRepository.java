package com.stschools.repository;

import com.stschools.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByCourse_Id(Long id);
    Video findVideoById(Long id);
}
