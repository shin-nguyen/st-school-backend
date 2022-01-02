package com.stschools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private String lecturer;
    private String language;
    private Integer price;
    private String image;
    private Integer subTotal;
    private Integer videoTotal;
    private List<VideoDTO> videos;
    private List<CommentDTO> comments;
    private List<ReviewDTO> reviews;
}
