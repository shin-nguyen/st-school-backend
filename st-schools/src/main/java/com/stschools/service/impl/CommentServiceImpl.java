package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.dto.OrderDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import com.stschools.entity.User;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.CommentRepository;
import com.stschools.service.BlogService;
import com.stschools.service.CommentService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public DataFetcher<CommentBlog> getCommentByQuery() {
        return dataFetchingEnvironment -> {
            Long blogId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return commentRepository.findById(blogId).get();
        };
    }

    @Override
    public DataFetcher<List<CommentBlog>> getAllCommentsByQuery() {
        return dataFetchingEnvironment -> commentRepository.findAllByOrderByIdAsc();
    }


    @Override
    public CommentBlog findCommentById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public List<CommentBlog> findAllComments(Long id) {
        return commentRepository.findAllByBlogId(id);
    }

    @Override
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    public CommentBlog update(CommentBlog commentBlog, Long id) throws ApiException {

        CommentBlog commentBlogOld = commentRepository.findCommentBlogById(commentBlog.getId());

        if (commentBlogOld == null) {
            throw new ApiException("Could not find  Comment with ID " + id);
        }

        commentBlogOld.setContent(commentBlog.getContent());

        return commentRepository.save(commentBlogOld);
    }

    @Override
    public CommentBlog addComment(CommentBlog commentBlog, Long id) throws ApiException {
        User user = userService.findUserById(id);
        commentBlog.setUser(user);

        return commentRepository.save(commentBlog);
    }

    @Override
    public Boolean addListComment(Long id, List<CommentBlogDTO> list) {
       commentRepository.saveAll( ModelMapperControl.mapAll(list,CommentBlog.class));
        return true;
    }
}
