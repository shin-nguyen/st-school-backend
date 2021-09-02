package com.stschools.service;

import com.stschools.dto.BlogDto;
import com.stschools.entity.Blog;

import java.util.List;

public interface BlogService {
    List<BlogDto> getBlogs();
    BlogDto save(BlogDto blogDto);
}
