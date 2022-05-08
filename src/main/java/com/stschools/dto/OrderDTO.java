package com.stschools.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private String createdTime;
    private Integer total;
    private Integer progress;
    private CourseDTO course;
    private UserDTO user;
    private Set<VideoDTO> videos;
}
