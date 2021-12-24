package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.entity.User;
import com.stschools.import_file.blogs.BlogExcelImporter;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.entity.Blog;
import com.stschools.service.BlogService;
import com.stschools.service.UserService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    public final Cloudinary cloudinary;
    private final UserService userService;
    private final BlogExcelImporter blogExcelImporter;

    @Override
    public DataFetcher<Blog> getBlogByQuery() {
        return dataFetchingEnvironment -> {
            Long blogId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return blogRepository.findById(blogId).get();
        };
    }

    @Override
    public DataFetcher<List<Blog>> getAllBlogsByQuery() {
        return dataFetchingEnvironment -> {
            String type = dataFetchingEnvironment.getArgument("type");
            List<Blog> list;

            switch (type) {
                case "true":
                    list = blogRepository.findAllByStatus(true);
                    break;
                case "false":
                    list = blogRepository.findAllByStatus(false);
                    break;
                default:
                    list = blogRepository.findAllByOrderByIdAsc();
            }
            return list;
        };
    }


    @Override
    @Transactional
    public Blog findBlogById(Long blogId) {
        blogRepository.updateView(blogId);
        return blogRepository.findById(blogId).get();
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Long deleteBlog(Long blogId) throws ApiException {
        Blog blogOld = blogRepository.findBlogById(blogId);

        if (blogOld == null) {
            throw new ApiException("Could not find  blog with ID " + blogId);
        }
        blogOld.setIsDeleted(true);
        blogRepository.save(blogOld);
        return blogId;
    }

    @Override
    public Blog update(Blog blog, Long id) throws ApiException {

        Blog blogOld = blogRepository.findBlogById(blog.getId());

        if (blogOld == null) {
            throw new ApiException("Could not find  blog with ID " + id);
        }

        blogOld.setContent(blog.getContent());
        blogOld.setTitle(blog.getTitle());

        return blogRepository.save(blogOld);
    }

    @Override
    public Blog addBlog(BlogRequest blog, Long id) throws IOException, ApiException {
        Map uploadResult = cloudinary.uploader().upload(blog.getFile().getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + blog.getFile().getOriginalFilename()));
        Blog blogNew = new Blog();
        blogNew.setImage(uploadResult.get("secure_url").toString());
        blogNew.setTitle(blog.getTitle());
        blogNew.setContent(blog.getContent());
        blogNew.setSummary("New Summary");

        User user = userService.findUserById(id);
        blogNew.setUser(user);

        blogNew.setStatus(false);


        return blogRepository.save(blogNew);
    }

    @Override
    public List<Blog> addBlog(MultipartFile file) throws IOException {
        List<Blog> blogs = blogExcelImporter.parseExcelFile(file.getInputStream());
        return blogRepository.saveAll(blogs);
    }

    @Override
    public DataFetcher<List<Blog>> getAllBlogsByMe() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            List<Blog> list = blogRepository.findAllByUserEmail(email);
            return list;
        };
    }

    @Override
    @Transactional
    public Blog updateBlogStatus(Long blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog.getStatus()) {
            blogRepository.updateBlogStatus(blogId, true);
        } else {
            blogRepository.updateBlogStatus(blogId, false);
        }
        blog.setStatus(!blog.getStatus());
        return blog;
    }




}
