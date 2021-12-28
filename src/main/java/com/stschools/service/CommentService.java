package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import com.stschools.payload.blog.BlogRequest;
import graphql.schema.DataFetcher;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    DataFetcher<CommentBlog> getCommentByQuery();

    DataFetcher<List<CommentBlog>> getAllCommentsByQuery();

    CommentBlog findCommentById(Long blogId);

    List<CommentBlog> findAllComments(Long id);


    //    List<Perfume> filter(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice);
//
//    List<Perfume> findByPerfumerOrderByPriceDesc(String perfumer);
//
//    List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);
//
//    Perfume savePerfume(Perfume perfume, MultipartFile file);
//
    Long deleteComment(Long commentId);

    CommentBlog update(CommentBlog comment, Long id) throws ApiException;

    CommentBlog addComment(CommentBlog comment, Long id) throws ApiException;

    Boolean addListComment(Long id, List<CommentBlogDTO> list);
}
