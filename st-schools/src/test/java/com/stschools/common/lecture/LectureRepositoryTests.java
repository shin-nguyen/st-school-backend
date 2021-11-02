package com.stschools.common.lecture;

import com.stschools.entity.Lecture;
import com.stschools.entity.Section;
import com.stschools.repository.LectureRepository;
import com.stschools.repository.SectionRepository;
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
public class LectureRepositoryTests {
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Test
    public void testAddLecture(){
        Section section2 = sectionRepository.getById(2L);

        List<Lecture> lectureList = Arrays.asList(
                new Lecture("Cài đặt môi trường",
                        "https://res.cloudinary.com/qscloud/video/upload/v1634697286/st-school/videos/C%C3%A0i_%C4%91%E1%BA%B7t_m%C3%B4i_tr%C6%B0%E1%BB%9Dng_t2h1sb.mp4",
                        1,
                        section2
                ),
                new Lecture("Khai báo biến",
                        "https://res.cloudinary.com/qscloud/video/upload/v1634826868/st-school/videos/Khai_b%C3%A1o_bi%E1%BA%BFn_ahj7jx.mp4",
                        2,
                        section2
                ),
                new Lecture("Vòng lặp For",
                        "https://res.cloudinary.com/qscloud/video/upload/v1634826935/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_For_p9zhrg.mp4",
                        3,
                        section2
        )
        );

        lectureRepository.saveAll(lectureList);
//        Iterable<Lecture> iterable = lectureRepository.findAll();
//        assertThat(iterable).size().isEqualTo(7);
    }
}
