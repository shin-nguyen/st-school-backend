package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stschools.entity.Comment;
import com.stschools.entity.User;
import lombok.Data;

@Data
public class ReplyCommentDTO {
    private Long id;
    private String content;
    private String createdTime;
    private User user;
    @JsonIgnore
    private Comment comment;
}
