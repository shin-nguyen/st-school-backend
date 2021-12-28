package com.stschools.common.blog;

import com.stschools.entity.Blog;
import com.stschools.entity.Topic;
import com.stschools.entity.User;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.TopicRepository;
import com.stschools.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BlogRepositoryTests {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;



    @Test
    public void testAddBlog() {
        List<Topic> topis = new ArrayList<>();
        topicRepository.findAll().forEach(topis::add);

        User user= userRepository.findByEmail("thongchuthanh2000@gmail.com");
        List<Blog> blogeList = Arrays.asList(
                new Blog("[Spring boot] Spring Cloud",
                        "Spring Cloud",
                        "Khi ngày càng có nhiều nhà phát triển Java học Spring Boot và Spring Cloud để phát triển các ứng dụng Java dựa trên đám mây, " +
                                "bạn có thể mong đợi nhiều sách được viết và phát hành về các chủ đề này. " +
                                "Hiện tại, chúng ta sẽ thấy một số tài nguyên tốt nhất để học " ,
                        true,
                        "https://res.cloudinary.com/qscloud/image/upload/v1632104647/st-school/images/java.png.png",
                        user,
                        topis),
                new Blog("Hướng dẫn sử dụng @Autowired trong Spring",
                        "Summary",
                        "Tổng quan Bắt đầu với Spring 2.5, framework đã giới thiệu tính năng Dependency Injection theo hướng chú thích. " +
                                "Chú thích chính của tính năng này là @Autowired. " +
                                "Nó cho phép Spring resolve và inject các collaborating beans vào bean của chúng ta. " +
                                "Tham Khảo thêm: Lập trình Front-end và Back-end là gì? Trong hướng",
                        true,
                        "https://res.cloudinary.com/qscloud/image/upload/v1632104647/st-school/images/java.png.png",
                        user,
                        topis
                )
        );

        blogRepository.saveAll(blogeList);

        Iterable<Blog> iterable = blogRepository.findAll();
        assertThat(iterable).size().isGreaterThan(0);
    }



    @Test
    public void testCountById() {
        Long countById = blogRepository.count();
        System.out.println(countById);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testCountViewById() {
        blogRepository.updateView(1L);
        Long countById = blogRepository.getById(1L).getView();
        System.out.println(countById);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testGetBlogById() {
        Blog blog = blogRepository.findBlogById(1L);
        System.out.println(blog);
        assertThat(blog).isNotNull();
    }

    @Test
    public void testBlogByStatus() {
        List<Blog> blogs = blogRepository.findAllByUserEmail("thongchuthanh2000@gmail.com");
        System.out.println(blogs);
        assertThat(blogs.size()).isGreaterThan(0);
    }
}
