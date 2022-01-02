package com.stschools.service.impl;

import com.cloudinary.api.exceptions.ApiException;
<<<<<<< HEAD
import com.stschools.dto.CommentDTO;
import com.stschools.entity.Comment;
import com.stschools.entity.User;
import com.stschools.repository.CommentRepository;
=======
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
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
import com.stschools.service.CommentService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public DataFetcher<Comment> getCommentByQuery() {
        return dataFetchingEnvironment -> {
            Long blogId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return commentRepository.findById(blogId).get();
        };
    }

    @Override
    public DataFetcher<List<Comment>> getAllCommentsByQuery() {
        return dataFetchingEnvironment -> commentRepository.findAllByOrderByIdAsc();
    }


    @Override
<<<<<<< HEAD
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public List<Comment> findAllComments(Long id) {
        return commentRepository.findAllByBlogId(id);
=======
    public CommentBlogDTO findCommentById(Long commentId) {
        CommentBlog comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(comment, CommentBlogDTO.class);
    }

    @Override
    public List<CommentBlogDTO> findAllComments(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findAllByBlogId(id), CommentBlogDTO.class);
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
    }

    @Override
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
<<<<<<< HEAD
    public Comment update(Comment comment, Long id) throws ApiException {

        Comment commentOld = commentRepository.findCommentBlogById(comment.getId());
=======
    @Transactional
    public CommentBlogDTO update(CommentBlogDTO commentBlog, Long id) throws ApiException {
        CommentBlog commentBlogOld = commentRepository.findCommentBlogById(commentBlog.getId());
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70

        if (commentOld == null) {
            throw new ApiException("Could not find  Comment with ID " + id);
        }
<<<<<<< HEAD

        commentOld.setContent(comment.getContent());

        return commentRepository.save(commentOld);
    }

    @Override
    public Comment addComment(Comment comment, Long id) throws ApiException {
        User user = userService.findUserById(id);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public Boolean addListComment(Long id, List<CommentDTO> list) {
       commentRepository.saveAll( ModelMapperControl.mapAll(list, Comment.class));
        return true;
=======
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
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
    }

    @Override
    public List<CommentDTO> getCommentsOfCourse(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findByCourseId(id), CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getCommentsOfBlog(Long id) {
        return ModelMapperControl.mapAll(commentRepository.findByBlogId(id), CommentDTO.class);
    }
}
