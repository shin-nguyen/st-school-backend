package com.stschools.service.impl;

import com.stschools.util.ModelMapperControl;
import com.stschools.dto.BlogDTO;
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
    public List<BlogDTO> getBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }

    @Override
    public BlogDTO save(BlogDTO blogDto) {
        Blog blog = ModelMapperControl.map(blogDto, Blog.class);
        return ModelMapperControl.map(blogRepository.save(blog), BlogDTO.class);
    }
}
