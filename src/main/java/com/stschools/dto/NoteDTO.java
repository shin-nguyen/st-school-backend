package com.stschools.dto;
import lombok.Data;

@Data
public class NoteDTO {
    private Long id;
    private String createdTime;
    private String updateTime;
    private String atTime;
    private VideoDTO video;
    private String content;
    private UserDTO user;
    private CourseDTO course;
}