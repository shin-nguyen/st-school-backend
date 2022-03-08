package com.stschools.dto;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CommentDTO {
    private Long id;
    private String createdTime;
    private String updateTime;
    @NotBlank
    private String content;
    private UserDTO user;
    @JsonIgnore
    private BlogDTO blog;
    @JsonIgnore
    private CourseDTO course;
    private List<ReplyCommentDTO> replies;
}
