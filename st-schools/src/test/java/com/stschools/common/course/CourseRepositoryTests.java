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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                        "Eng",
                        96,
                        "https://res.cloudinary.com/qscloud/image/upload/v1635049180/st-school/images/html.png.png"
                        ),
                new Course("Learn CSS",
                        "Start learning CSS with the w3schools fundamentals course. CSS is the language we use to style an HTML document.', 'css.png', 'Learn CSS",
                        "Eng",
                        90,
                        "https://res.cloudinary.com/qscloud/image/upload/v1635049169/st-school/images/css.png.png"
                )
        );

        courseRepository.saveAll(courseList);

        Iterable<Course> iterable = courseRepository.findAll();
        assertThat(iterable).size().isEqualTo(2);
    }

    @Test
    public void findCoursesByUserId() {
        System.out.println(courseRepository.findCoursesByUserId(2L));
        Iterable<Course> iterable = courseRepository.findCoursesByUserId(2L);
        assertThat(iterable).size().isEqualTo(2);
    }

    @Test

    public void testFindCourseByName(){
        String name = "Learn HTML";
        List<Course> courseList = courseRepository.findCourseByName(name);
        System.out.println(courseList.get(0));
        assertNotNull(courseList.get(0));
    }

    @Test
    public void findCoursesByUserAndOrder() {
        List<Course> list = courseRepository.findCoursesByNotInOrder(1L);
        System.out.println(list);

        //Nay tu chinh tham so nha
        assertThat(list).size().isEqualTo(0);
    }

}
