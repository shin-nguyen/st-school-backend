package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.dto.ReplyCommentDTO;
import com.stschools.dto.UserDTO;
import com.stschools.exception.ApiRequestException;
import com.stschools.exception.InputFieldException;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.CommentService;
import com.stschools.service.UserService;
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
    private final UserService userService;

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getCommentsOfCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getCommentsOfCourse(id));
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<List<?>> getCommentsOfBlog(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getCommentsOfBlog(id));
    }

    @PostMapping(value ="/add")
    public ResponseEntity<CommentDTO> registerPost(@RequestBody CommentDTO commentDTO,
                                                   @CurrentUser UserPrincipal user,
                                                   BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.addComment(commentDTO,user.getId()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                            @Valid @RequestBody CommentDTO request,
                                            BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(commentService.update(request,user.getId()));
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "commentId") Long commentId) {
        if (commentService.findCommentById(commentId)==null){
            throw new ApiRequestException("Comment is null!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @PostMapping("/{commentId}/replies")
    public ResponseEntity<CommentDTO> replyComment(@PathVariable(value = "commentId") Long commentId,
                                                        @RequestBody ReplyCommentDTO replyCommentDTO,
                                                        @CurrentUser UserPrincipal user) throws ApiException {
        UserDTO userDTO = userService.findUserById(user.getId());
        replyCommentDTO.setUser(userDTO);
        return ResponseEntity.ok(commentService.replyComment(commentId, replyCommentDTO));
    }
}
