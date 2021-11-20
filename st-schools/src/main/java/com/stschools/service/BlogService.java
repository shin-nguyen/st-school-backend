package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.BlogDTO;
import com.stschools.entity.Blog;
import com.stschools.payload.blog.BlogRequest;
import graphql.schema.DataFetcher;

import java.io.IOException;
import java.util.List;

public interface BlogService {
    DataFetcher<Blog> getBlogByQuery();

    DataFetcher<List<Blog>> getAllBlogsByQuery();

    Blog findBlogById(Long blogId);

    List<Blog> findAllBlogs();

//    List<Perfume> filter(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice);
//
//    List<Perfume> findByPerfumerOrderByPriceDesc(String perfumer);
//
//    List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);
//
//    Perfume savePerfume(Perfume perfume, MultipartFile file);
//
    Long deleteBlog(Long blogId);

    Blog update(Blog blog, Long id) throws ApiException;

    Blog addBlog(BlogRequest blog, Long id) throws IOException, ApiException;
}
