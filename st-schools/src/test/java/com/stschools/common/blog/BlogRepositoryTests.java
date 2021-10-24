package com.stschools.common.blog;

import com.stschools.entity.Blog;
import com.stschools.entity.Course;
import com.stschools.entity.Topic;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.TopicRepository;
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

    @Test
    public void testAddBlog() {
        List<Topic> topis = new ArrayList<>();
        topicRepository.findAll().forEach(topis::add);

        List<Blog> blogeList = Arrays.asList(
                new Blog("Title",
                        "Description",
                        "Content .........................",
                        true,
                        topis),
                new Blog("Learn CSS",
                        "Start learning CSS with the w3schools fundamentals course. CSS is the language we use to style an HTML document.', 'css.png', 'Learn CSS",
                        "13 Hour",
                        false, topis
                )
        );

        blogRepository.saveAll(blogeList);

        Iterable<Blog> iterable = blogRepository.findAll();
        assertThat(iterable).size().isGreaterThan(0);
    }
}
