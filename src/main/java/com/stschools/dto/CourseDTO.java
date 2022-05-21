package com.stschools.dto;

import com.stschools.common.enums.Catogory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String name;
    private String about;
    private String description;
    private String lecturer;
    private String requirements;
    private String isFor;
    private String language;
    private String topic;
    private Integer price;
    private Integer subPrice;
    private String image;
    private Integer subTotal;
    private Integer commentTotal;
    private Integer rateTotal;
    private Double averageRate;
    private Integer videoTotal;
    private Catogory category;

    private Double duration;
    private String createdTime;
    private String updateTime;
//    private List<VideoDTO> videos;
//    private List<CommentDTO> comments;
//    private List<ReviewDTO> reviews;
}
