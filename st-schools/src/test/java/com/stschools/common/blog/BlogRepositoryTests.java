package com.stschools.common.blog;

import com.stschools.entity.Blog;
import com.stschools.entity.Course;
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
                new Blog("[Spring Boot] Entity",
                        "Summarry Spring",
                        "Github. " ,
                        true,
                        "https://res.cloudinary.com/qscloud/image/upload/v1632104647/st-school/images/java.png.png",
                        user,
                        topis),
                new Blog("Learn Tiktok",
                        "Summary",
                        "Content",
                        false,
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
