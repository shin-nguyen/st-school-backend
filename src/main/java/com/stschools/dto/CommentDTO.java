package com.stschools.dto;

import lombok.Data;
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
    private BlogDTO blog;
    private CourseDTO course;
    private List<ReplyCommentDTO> replies;
}
