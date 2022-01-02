package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.exception.ApiRequestException;
import com.stschools.exception.InputFieldException;
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
    private final CommentService commentService;
<<<<<<< HEAD

=======
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<?>> getAllComments(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.findAllComments(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                            @Valid @RequestBody CommentDTO request,
                                            BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.update(request,user.getId()));
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "commentId") Long commentId) {
        if (commentService.findCommentById(commentId)==null){
            throw new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

<<<<<<< HEAD
=======

>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
    @PostMapping(value ="/add")
    public ResponseEntity<CommentDTO> registerPost(@RequestBody CommentDTO commentDTO,
                                                   @CurrentUser UserPrincipal user,
                                                   BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
<<<<<<< HEAD
            return ResponseEntity.ok(commentMapper.addComment(user.getId(), commentDTO));
=======
            return ResponseEntity.ok(commentService.addComment(commentBlogDTO,user.getId()));
>>>>>>> 4cba097887d17b0eccf17efd17e606cd0ca38b70
        }
    }
    @PostMapping(value ="/add/all")
    public ResponseEntity<?> addListComment(@RequestBody List<CommentDTO> list,
                                                       @CurrentUser UserPrincipal user,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.addListComment( user.getId(),list));
        }
    }
}
