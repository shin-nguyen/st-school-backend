package com.stschools.repository;

import com.stschools.entity.ActivityProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityProgressRepository extends JpaRepository<ActivityProgress, Long> {
    ActivityProgress findByUserId(Long id);
}