package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.CourseDTO;
import com.stschools.entity.User;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.entity.Blog;
import com.stschools.service.BlogService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    public final Cloudinary cloudinary;
    private final UserService userService;

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

            switch (type){
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
    public DataFetcher<List<Blog>> getAllBlogsByMe() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            List<Blog> list = blogRepository.findAllByUserEmail(email);
            return list;
        };
    }

    @Override
    public Blog updateBlogStatus(Long blogId, String status) {
        switch (status){
            case "TRUE":
                blogRepository.updateBlogStatus(blogId,true);
            default:
                blogRepository.updateBlogStatus(blogId,false);
        }

        return blogRepository.findBlogById(blogId);
    }
}
