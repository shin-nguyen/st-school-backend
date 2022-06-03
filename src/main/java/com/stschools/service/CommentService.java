package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.dto.ReplyCommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO findCommentById(Long blogId);

    Long deleteComment(Long commentId);

    CommentDTO update(CommentDTO comment, Long id) throws ApiException;

    CommentDTO addComment(CommentDTO comment, Long id) throws ApiException;

    List<CommentDTO> getCommentsOfCourse(Long id);

    List<CommentDTO> getCommentsOfBlog(Long id);

    CommentDTO replyComment(Long commentId, ReplyCommentDTO replyCommentDTO);
}
