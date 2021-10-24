package com.stschools.common.course;

import com.stschools.entity.Course;
import com.stschools.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CourseRepositoryTests {
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testAddCourse() {

        List<Course> courseList = Arrays.asList(
                new Course("Learn HTML",
                        "Start learning HTML with the w3schools fundamentals course. HTML is the standard markup language for creating Web pages.",
                        "20 Hour",
                        "Eng",
                        96,
                        "https://cdn2.vectorstock.com/i/1000x1000/58/31/loading-icon-on-black-vector-24545831.jpg"
                ),
                new Course("Learn CSS",
                        "Start learning CSS with the w3schools fundamentals course. CSS is the language we use to style an HTML document.', 'css.png', 'Learn CSS",
                        "13 Hour",
                        "Eng",
                        90,
                        "https://cdn2.vectorstock.com/i/1000x1000/58/31/loading-icon-on-black-vector-24545831.jpg"
                )
        );

        courseRepository.saveAll(courseList);

        Iterable<Course> iterable = courseRepository.findAll();
        assertThat(iterable).size().isEqualTo(2);
    }

    @Test
    public void findCoursesByUserId() {
        System.out.println(courseRepository.findCoursesByUserId(1L));
        Iterable<Course> iterable = courseRepository.findCoursesByUserId(1L);
        assertThat(iterable).size().isEqualTo(2);
    }

}
