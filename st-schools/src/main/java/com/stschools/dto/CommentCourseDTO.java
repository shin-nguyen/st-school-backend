package com.stschools.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class  CommentCourseDTO {

    private Long id;
    private String createdTime;
    private String updateTime;
    @NotBlank
    private String content;
    private UserDTO user;
    private CourseDTO course;
}
