package com.stschools.dto;

import lombok.Data;

@Data
public class ReplyCommentDTO {
    private Long id;
    private String content;
    private String createdTime;
    private UserDTO user;
//    private CommentDTO comment;
}
