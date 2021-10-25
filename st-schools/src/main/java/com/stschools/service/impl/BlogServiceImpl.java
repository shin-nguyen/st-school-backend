package com.stschools.service.impl;

import com.stschools.repository.BlogRepository;
import com.stschools.entity.Blog;
import com.stschools.service.BlogService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

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
}
