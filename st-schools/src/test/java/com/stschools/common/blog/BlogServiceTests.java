package com.stschools.common.blog;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.common.enums.Role;
import com.stschools.entity.Blog;
import com.stschools.entity.User;
import com.stschools.import_file.blogs.BlogExcelImporter;
import com.stschools.repository.UserRepository;
import com.stschools.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogServiceTests {
    @Autowired
    BlogExcelImporter blogExcelImporter;

    @Test
    public void testAddBlogByExcel() throws FileNotFoundException {
        File file = new File("Blogs_Template.xlsx");
        InputStream inputStream = new FileInputStream(file);

        List<Blog> blogs = blogExcelImporter.parseExcelFile(inputStream);
        System.out.println(blogs);
        assertThat(blogs).size().isGreaterThan(0);
    }
}
