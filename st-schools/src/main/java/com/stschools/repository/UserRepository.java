package com.stschools.repository;

import com.stschools.entity.User;
import com.stschools.paging.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends SearchRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where concat(u.id, ' ', u.email , ' ',u.name) LIKE %?1% ")
    Page<User> findAll(String keyword, Pageable pageable);

    Long countById(Integer id);
}
