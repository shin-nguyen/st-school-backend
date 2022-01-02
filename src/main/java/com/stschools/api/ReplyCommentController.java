package com.stschools.api;

import com.stschools.dto.ReplyCommentDTO;
import com.stschools.service.ReplyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/comment/reply")
public class ReplyCommentController {

    @Autowired
    ReplyCommentService replyCommentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReply(@PathVariable(name = "id") Long id){
        try{
            return new ResponseEntity<>(replyCommentService.getReplyOfComment(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> replyComment(@RequestBody ReplyCommentDTO replyCommentDTO){
        try{
            return new ResponseEntity<>(replyCommentService.addReply(replyCommentDTO), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
