package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.exception.ApiRequestException;
import com.stschools.exception.InputFieldException;
import com.stschools.mapper.CommentMapper;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;
//    private final GraphQLProvider graphQLProvider;

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentMapper.findCommentById(id));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<?>> getAllComments(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentMapper.findAllComments(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                            @Valid @RequestBody CommentBlogDTO request,
                                            BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentMapper.updateComment(user.getId(), request));
        }
    }

//    @PostMapping("/search")
//    public ResponseEntity<List<PerfumeResponse>> findPerfumesByFilterParams(@RequestBody PerfumeSearchRequest filter) {
//        return ResponseEntity.ok(blogMapper.filter(filter.getPerfumers(), filter.getGenders(), filter.getPrices(), filter.isSortByPrice()));
//    }
//
//    @PostMapping("/search/gender")
//    public ResponseEntity<List<PerfumeResponse>> findByPerfumeGender(@RequestBody PerfumeSearchRequest filter) {
//        return ResponseEntity.ok(blogMapper.findByPerfumeGenderOrderByPriceDesc(filter.getPerfumeGender()));
//    }
//
//    @PostMapping("/search/perfumer")
//    public ResponseEntity<List<PerfumeResponse>> findByPerfumer(@RequestBody PerfumeSearchRequest filter) {
//        return ResponseEntity.ok(blogMapper.findByPerfumerOrderByPriceDesc(filter.getPerfumer()));
//    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "commentId") Long commentId) {
        if (commentMapper.findCommentById(commentId)==null){
            throw new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

//    @PostMapping("/graphql/ids")
//    public ResponseEntity<ExecutionResult> getBlogsByIdsQuery(@RequestBody GraphQLRequest request) {
//        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//    }
//
//    @PostMapping("/graphql/blogs")
//    public ResponseEntity<ExecutionResult> getAllBlogsByQuery(@RequestBody GraphQLRequest request) {
//        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//    }
//
//    @PostMapping("/graphql/blog")
//    public ResponseEntity<ExecutionResult> getBlogByQuery(@RequestBody GraphQLRequest request) {
//        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
//    }

    @PostMapping(value ="/add")
    public ResponseEntity<CommentBlogDTO> registerPost(@RequestBody CommentBlogDTO commentBlogDTO,
                                                    @CurrentUser UserPrincipal user,
                                                BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentMapper.addComment(user.getId(), commentBlogDTO));
        }
    }
    @PostMapping(value ="/add/all")
    public ResponseEntity<?> addListComment(@RequestBody List<CommentBlogDTO> list,
                                                       @CurrentUser UserPrincipal user,
                                                       BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.addListComment( user.getId(),list));
        }
    }
}
