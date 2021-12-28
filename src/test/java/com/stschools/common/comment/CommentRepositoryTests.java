package com.stschools.common.comment;

import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import com.stschools.entity.Topic;
import com.stschools.entity.User;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.CommentRepository;
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
public class CommentRepositoryTests {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testAddCommentBlog() {
        User user= userRepository.findByEmail("thongchuthanh2000@gmail.com");
        Blog blog = blogRepository.findBlogById(1L);
        List<CommentBlog> commentBlogList = Arrays.asList(
                new CommentBlog("Good job, bro",
                        user,
                        blog),
                new CommentBlog("Thanks, very helpful",
                        user,
                        blog
                )
        );

        commentRepository.saveAll(commentBlogList);

        Iterable<CommentBlog> iterable = commentRepository.findAll();
        assertThat(iterable).size().isGreaterThan(0);
    }
}
