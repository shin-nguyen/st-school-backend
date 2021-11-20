package com.stschools.repository;

import com.stschools.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o ORDER BY o.createdTime DESC")
    Page<Order> findByTop(Pageable pageable);

    @Query("select sum (o.course.price) from Order o")
    Long getSumImcome();

    Long countByCourseIdAndUserId(Long course, Long user);

}
