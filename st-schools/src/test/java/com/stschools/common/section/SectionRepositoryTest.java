package com.stschools.common.section;

import com.stschools.entity.Course;
import com.stschools.entity.Section;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.SectionRepository;
import com.stschools.util.ModelMapperControl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SectionRepositoryTest {
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testAddSection(){
        Course course = courseRepository.findCourseById(1L);

        List<Section> sectionList = Arrays.asList(
                new Section("Section1",course),
                new Section("Section2",course),
                new Section("Section3",course),
                new Section("Section4",course),
                new Section("Section5",course),
                new Section("Section6",course),
                new Section("Section7",course)
        );

        sectionRepository.saveAll(sectionList);
    }
}
