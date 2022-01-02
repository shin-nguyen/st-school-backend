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

    CommentBlogDTO findCommentById(Long blogId);

    List<CommentBlogDTO> findAllComments(Long id);

    Long deleteComment(Long commentId);

    CommentBlogDTO update(CommentBlogDTO comment, Long id) throws ApiException;

    CommentBlogDTO addComment(CommentBlogDTO comment, Long id) throws ApiException;

    Boolean addListComment(Long id, List<CommentBlogDTO> list);
}
