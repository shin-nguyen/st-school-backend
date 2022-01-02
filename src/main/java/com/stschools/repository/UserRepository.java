package com.stschools.repository;

import com.stschools.common.enums.Role;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.paging.SearchRepository;
import com.stschools.payload.dashboard.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends SearchRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where concat(u.id, ' ', u.email , ' ',u.lastName, ' ',u.firstName) LIKE %?1% ")
    Page<User> findAll(String keyword, Pageable pageable);

    List<User> findAll();

    List<User> findAlLByRoles(Role role);

    Long countById(Long id);

    List<User> findAllByOrderByIdAsc();

    User findByActivationCode(String code);

    User findByPasswordResetCode(String code);

    List<User> findAllByRoles(Role role);


    @Query(value = "SELECT new com.stschools.payload.dashboard.UserResponse(u.firstName,u.lastName,count(o.id),sum(o.course.price) )  from  User u join Order o on o.user.id = u.id group by u.id order by sum(o.course.price) desc ")
    Page<UserResponse> getTopBy5(Pageable pageable);
}
