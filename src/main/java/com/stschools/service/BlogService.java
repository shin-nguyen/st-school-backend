package com.stschools.service;

import com.stschools.dto.BlogDTO;
import com.stschools.dto.BlogUserLoveDTO;
import com.stschools.payload.blog.BlogRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    BlogDTO getBlog(Long blogId);

    List<BlogDTO> getAllBlogs();

    BlogDTO findBlogById(Long blogId);

    List<BlogDTO> findAllBlogs();

    List<BlogDTO> getAllBlogsByLove(Long id);

    Long deleteBlog(Long blogId);

    BlogDTO update(BlogDTO blog, Long id);

    BlogDTO addBlog(BlogRequest blog, Long id) throws IOException;

    List<BlogDTO> addBlog(MultipartFile file) throws IOException;

    List<BlogDTO> getAllBlogsByMe(Long userId);

    BlogDTO updateBlogStatus(Long blogId);

    List<BlogUserLoveDTO> updateLove(Long blogId, Long id);
}

