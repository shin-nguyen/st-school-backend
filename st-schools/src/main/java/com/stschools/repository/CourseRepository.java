package com.stschools.repository;

import com.stschools.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseById(Long id);
    List<Course> findCourseByName(String name);

    @Query("""
            SELECT new Course(c.id, c.name, c.description, c.image)
            FROM Course c join Order o on c.id = o.course.id where o.user.id = ?1
            """)
    List<Course> findCoursesByUserId(Long id);

    @Query("SELECT c FROM Course c where c.id not in (select o.course.id from Order o where o.user.id =?1)")
    List<Course> findCoursesByNotInOrder(Long id);
}
