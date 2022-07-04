package com.stschools.service;

import com.stschools.dto.BlogDTO;
import com.stschools.payload.blog.BlogRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    BlogDTO findBlogById(Long blogId,Long id);

    List<BlogDTO> getAllBlogsByLove(Long id);

    Long deleteBlog(Long blogId);

    BlogDTO update(BlogDTO blog, Long id);

    BlogDTO addBlog(BlogRequest blog, Long id) throws IOException;

    List<BlogDTO> addBlog(MultipartFile file) throws IOException;

    List<BlogDTO> getAllBlogsByMe(Long userId);

    BlogDTO updateBlogStatus(Long blogId);

    BlogDTO updateLove(Long blogId, Long id);

    List<BlogDTO> getTopNew(Long id);

    List<BlogDTO> getTopView(Long id);
}

