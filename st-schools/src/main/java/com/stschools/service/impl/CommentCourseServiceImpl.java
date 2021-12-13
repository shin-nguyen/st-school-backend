package com.stschools.service.impl;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentCourseDTO;
import com.stschools.entity.CommentCourse;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.CommentCourseRepository;
import com.stschools.service.CommentCourseService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentCourseServiceImpl implements CommentCourseService {

    private final CommentCourseRepository commentRepository;
    private final UserService userService;

    @Override
    public CommentCourseDTO findCommentById(Long commentId) {
        return ModelMapperControl.map(commentRepository.findById(commentId).get(),CommentCourseDTO.class);
    }

    @Override
    public List<CommentCourseDTO> findAllComments(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findAllByCourseId(id),CommentCourseDTO.class);
    }

    @Override
    public Long deleteComment(Long commentId) {
        if (findCommentById(commentId)==null){
            throw new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST);
        }
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    public CommentCourseDTO update(CommentCourseDTO commentBlog, Long id) throws ApiException {

        CommentCourse commentBlogOld = commentRepository.findCommentCourseById(commentBlog.getId());

        if (commentBlogOld == null) {
            throw new ApiException("Could not find  Comment with ID " + id);
        }

        commentBlogOld.setContent(commentBlog.getContent());
        return ModelMapperControl.map(commentRepository.save(commentBlogOld),CommentCourseDTO.class);
    }

    @Override
    public CommentCourseDTO addComment(CommentCourseDTO commentCourseDTO, Long id) throws ApiException {
        User user = userService.findUserById(id);
        CommentCourse commentCourse =  ModelMapperControl.map(commentCourseDTO, CommentCourse.class);

        commentCourse.setUser(user);
        return ModelMapperControl.map(commentRepository.save(commentCourse),CommentCourseDTO.class);
    }

}
