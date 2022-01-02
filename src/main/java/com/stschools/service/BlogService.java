package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.BlogDTO;
import com.stschools.entity.Blog;
import com.stschools.payload.blog.BlogRequest;
import graphql.schema.DataFetcher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    DataFetcher<Blog> getBlogByQuery();

    DataFetcher<List<Blog>> getAllBlogsByQuery();

    BlogDTO findBlogById(Long blogId);

    List<BlogDTO> findAllBlogs();

    Long deleteBlog(Long blogId) ;

    BlogDTO update(BlogDTO blog, Long id) ;

    BlogDTO addBlog(BlogRequest blog, Long id) throws IOException;
    List<BlogDTO> addBlog(MultipartFile file) throws IOException;

    DataFetcher<List<Blog>> getAllBlogsByMe();

    BlogDTO updateBlogStatus(Long blogId);

}

