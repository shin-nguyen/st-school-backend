package com.stschools.repository;

import com.stschools.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM tbl_user u WHERE u.role.name = 'ROLE_CUSTOMER'")
    List<User> getCustomers();
}
