package com.stschools.service;

import com.stschools.dto.BlogDTO;
import com.stschools.entity.Blog;
import graphql.schema.DataFetcher;

import java.util.List;

public interface BlogService {
    DataFetcher<Blog> getBlogByQuery();

    DataFetcher<List<Blog>> getAllBlogsByQuery();

    DataFetcher<List<Blog>> getAllBlogsByIdsQuery();

    Blog findBlogById(Long blogId);

    List<Blog> findAllBlogs();

    List<Blog> findBlogsByIds(List<Long> blogsId);

//    List<Perfume> filter(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice);
//
//    List<Perfume> findByPerfumerOrderByPriceDesc(String perfumer);
//
//    List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);
//
//    Perfume savePerfume(Perfume perfume, MultipartFile file);
//
    Long deleteBlog(Long blogId);
}
