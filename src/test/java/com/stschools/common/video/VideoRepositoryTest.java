//package com.stschools.common.video;
//
//import com.stschools.entity.Course;
//import com.stschools.entity.Video;
//import com.stschools.repository.CourseRepository;
//import com.stschools.repository.VideoRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@Rollback(false)
//public class VideoRepositoryTest {
//    @Autowired
//    VideoRepository videoRepository;
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Test
//    public void testAddSection(){
//        Course course = courseRepository.findCourseById(1L);
//
//        List<Video> videoList = Arrays.asList(
//                new Video("Environment Settings", "https://res.cloudinary.com/qscloud/video/upload/v1636288871/st-school/videos/C%C3%A0i%20%C4%91%E1%BA%B7t%20m%C3%B4i%20tr%C6%B0%E1%BB%9Dng.mp4.mp4",course),
//                new Video("Declare variable","https://res.cloudinary.com/qscloud/video/upload/v1634826868/st-school/videos/Khai_b%C3%A1o_bi%E1%BA%BFn_ahj7jx.mp4",course),
//                new Video("For Loop","https://res.cloudinary.com/qscloud/video/upload/v1634826935/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_For_p9zhrg.mp4",course),
//                new Video("While Loop","https://res.cloudinary.com/qscloud/video/upload/v1635864791/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_While_pom4j7.mp4",course),
//                new Video("ID and Class", "https://res.cloudinary.com/qscloud/video/upload/v1636288871/st-school/videos/C%C3%A0i%20%C4%91%E1%BA%B7t%20m%C3%B4i%20tr%C6%B0%E1%BB%9Dng.mp4.mp4",course),
//                new Video("Overview","https://res.cloudinary.com/qscloud/video/upload/v1634826868/st-school/videos/Khai_b%C3%A1o_bi%E1%BA%BFn_ahj7jx.mp4",course),
//                new Video("Comment","https://res.cloudinary.com/qscloud/video/upload/v1634826935/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_For_p9zhrg.mp4",course),
//                new Video("Use HTML and CSS","https://res.cloudinary.com/qscloud/video/upload/v1635864791/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_While_pom4j7.mp4",course),
//                new Video("What is...", "https://res.cloudinary.com/qscloud/video/upload/v1636288871/st-school/videos/C%C3%A0i%20%C4%91%E1%BA%B7t%20m%C3%B4i%20tr%C6%B0%E1%BB%9Dng.mp4.mp4",course),
//                new Video("Common item","https://res.cloudinary.com/qscloud/video/upload/v1634826868/st-school/videos/Khai_b%C3%A1o_bi%E1%BA%BFn_ahj7jx.mp4",course),
//                new Video("End Course","https://res.cloudinary.com/qscloud/video/upload/v1634826935/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_For_p9zhrg.mp4",course),
//                new Video("Total","https://res.cloudinary.com/qscloud/video/upload/v1635864791/st-school/videos/V%C3%B2ng_l%E1%BA%B7p_While_pom4j7.mp4",course)
//
//        );
//
//        videoRepository.saveAll(videoList);
//        Iterable<Video> iterable = videoRepository.findAll();
//        assertThat(iterable).size().isGreaterThan(0);
//    }
//}
