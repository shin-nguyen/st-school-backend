package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.entity.Comment;
import graphql.schema.DataFetcher;

import java.util.List;

public interface CommentService {
    DataFetcher<Comment> getCommentByQuery();

    DataFetcher<List<Comment>> getAllCommentsByQuery();

<<<<<<< HEAD
    Comment findCommentById(Long blogId);

    List<Comment> findAllComments(Long id);

    Long deleteComment(Long commentId);

    Comment update(Comment comment, Long id) throws ApiException;

    Comment addComment(Comment comment, Long id) throws ApiException;

    Boolean addListComment(Long id, List<CommentDTO> list);
=======
    CommentBlogDTO findCommentById(Long blogId);

    List<CommentBlogDTO> findAllComments(Long id);

    Long deleteComment(Long commentId);

    CommentBlogDTO update(CommentBlogDTO comment, Long id) throws ApiException;

    CommentBlogDTO addComment(CommentBlogDTO comment, Long id) throws ApiException;
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70

    ///////////////
    List<CommentDTO> getCommentsOfCourse(Long id);
    List<CommentDTO> getCommentsOfBlog(Long id);
}
