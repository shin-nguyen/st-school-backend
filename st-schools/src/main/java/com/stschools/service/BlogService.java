package com.stschools.service;

import com.stschools.dto.BlogDTO;

import java.util.List;

public interface BlogService {
    List<BlogDTO> getBlogs();
    BlogDTO save(BlogDTO blogDto);
}
