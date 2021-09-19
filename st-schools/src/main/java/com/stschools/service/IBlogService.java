package com.stschools.service;

import com.stschools.dto.BlogDto;
import com.stschools.entity.Blog;

import java.util.List;

public interface IBlogService {
    List<BlogDto> getBlogs();
    BlogDto save(BlogDto blogDto);
}
