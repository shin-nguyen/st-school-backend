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
        return dataFetchingEnvironment -> blogRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<List<Blog>> getAllBlogsByIdsQuery() {
        return dataFetchingEnvironment -> {
            List<String> objects =dataFetchingEnvironment.getArgument("ids");
            List<Long> blogsId = objects.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return blogRepository.findByIdIn(blogsId);
        };
    }

    @Override
    public Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId).get();
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Blog> findBlogsByIds(List<Long> blogsId) {
        return blogRepository.findByIdIn(blogsId);
    }

    @Override
    public Long deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
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
    public BlogDTO addBlog(BlogRequest blog, Long id) throws IOException {
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
        return ModelMapperControl.map(blogRepository.save(blogNew), BlogDTO.class);
    }
}
