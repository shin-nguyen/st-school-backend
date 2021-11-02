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

import static org.assertj.core.api.Assertions.assertThat;

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
                new Section("Giới thiệu",course),
                new Section("Khởi động",course),
                new Section("Chuyên mục 1",course),
                new Section("Chuyên mục 2",course),
                new Section("Nâng cao",course),
                new Section("Thực hành",course),
                new Section("Tổng kết",course)
        );

        sectionRepository.saveAll(sectionList);
        Iterable<Section> iterable = sectionRepository.findAll();
        assertThat(iterable).size().isEqualTo(7);
    }
}
