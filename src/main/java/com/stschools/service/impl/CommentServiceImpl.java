package com.stschools.service.impl;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.entity.Comment;
import com.stschools.entity.User;
import com.stschools.repository.CommentRepository;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.UserRepository;
import com.stschools.service.CommentService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDTO findCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(comment, CommentDTO.class);
    }

    @Override
    public Long deleteComment(Long commentId) {
        return null;
    }

    @Override
    public CommentDTO update(CommentDTO comment, Long id) throws ApiException {
        return null;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentBlog, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        Comment commentBlogOld = ModelMapperControl.map(commentBlog, Comment.class);
        commentBlogOld.setUser(user);
        return ModelMapperControl.map(commentRepository.save(commentBlogOld), CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getCommentsOfCourse(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findAllByCourseId(id), CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getCommentsOfBlog(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findAllByBlogId(id), CommentDTO.class);
    }
}
