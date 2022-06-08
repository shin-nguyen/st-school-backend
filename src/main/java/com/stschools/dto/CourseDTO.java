package com.stschools.dto;

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
    private Double duration;
    private String createdTime;
    private String updateTime;
}