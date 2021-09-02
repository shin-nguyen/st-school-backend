package com.stschools.service.impl;

import com.stschools.util.ModelMapperControl;
import com.stschools.dto.BlogDto;
import com.stschools.entity.Blog;
import com.stschools.repository.BlogRepository;
import com.stschools.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public List<BlogDto> getBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return ModelMapperControl.mapAll(blogs, BlogDto.class);
    }

    @Override
    public BlogDto save(BlogDto blogDto) {
        Blog blog = ModelMapperControl.map(blogDto, Blog.class);
        return ModelMapperControl.map(blogRepository.save(blog), BlogDto.class);
    }
}
