package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.dto.CommentCourseDTO;
import com.stschools.entity.CommentBlog;
import graphql.schema.DataFetcher;

import java.util.List;

public interface CommentCourseService {
    CommentCourseDTO findCommentById(Long blogId);

    List<CommentCourseDTO> findAllComments(Long id);

    Long deleteComment(Long commentId);

    CommentCourseDTO update(CommentCourseDTO comment, Long id) throws ApiException;

    CommentCourseDTO addComment(CommentCourseDTO comment, Long id) throws ApiException;

}
