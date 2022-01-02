package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDTO findCommentById(Long blogId);

    List<CommentDTO> findAllComments(Long id);

    Long deleteComment(Long commentId);

    CommentDTO update(CommentDTO comment, Long id) throws ApiException;

    CommentDTO addComment(CommentDTO comment, Long id) throws ApiException;

//    CommentBlogDTO findCommentById(Long blogId);
//
//    List<CommentBlogDTO> findAllComments(Long id);
//
//    Long deleteComment(Long commentId);
//
//    CommentBlogDTO update(CommentBlogDTO comment, Long id) throws ApiException;
//
//    CommentBlogDTO addComment(CommentBlogDTO comment, Long id) throws ApiException;

    List<CommentDTO> getCommentsOfCourse(Long id);
    List<CommentDTO> getCommentsOfBlog(Long id);
}
