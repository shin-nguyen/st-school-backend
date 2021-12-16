package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentCourseDTO;
import com.stschools.exception.InputFieldException;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.CommentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments/course")
public class CommentCourseController {

    private final CommentCourseService commentService;


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
                                            @Valid @RequestBody CommentCourseDTO request,
                                            BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.update(request,user.getId() ));
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }


    @PostMapping(value ="/add")
    public ResponseEntity<CommentCourseDTO> registerPost(@RequestBody CommentCourseDTO commentCourseDTO,
                                                    @CurrentUser UserPrincipal user,
                                                BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.addComment(commentCourseDTO,user.getId()));
        }
    }

}
