package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.dto.OrderDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.CommentRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.BlogService;
import com.stschools.service.CommentService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

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
    public CommentBlogDTO findCommentById(Long commentId) {
        CommentBlog comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(comment, CommentBlogDTO.class);
    }

    @Override
    public List<CommentBlogDTO> findAllComments(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findAllByBlogId(id), CommentBlogDTO.class);
    }

    @Override
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    @Transactional
    public CommentBlogDTO update(CommentBlogDTO commentBlog, Long id) throws ApiException {
        CommentBlog commentBlogOld = commentRepository.findCommentBlogById(commentBlog.getId());

        if (commentBlogOld == null) {
            throw new ApiException("Could not find  Comment with ID " + id);
        }
        commentBlogOld.setContent(commentBlog.getContent());
        return ModelMapperControl.map(commentRepository.save(commentBlogOld), CommentBlogDTO.class);
    }

    @Override
    public CommentBlogDTO addComment(CommentBlogDTO commentBlog, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        CommentBlog commentBlogOld = ModelMapperControl.map(commentBlog, CommentBlog.class);
        commentBlogOld.setUser(user);
        return ModelMapperControl.map(commentRepository.save(commentBlogOld), CommentBlogDTO.class);
    }

    @Override
    public Boolean addListComment(Long id, List<CommentBlogDTO> list) {
       commentRepository.saveAll( ModelMapperControl.mapAll(list,CommentBlog.class));
       return true;
    }
}
