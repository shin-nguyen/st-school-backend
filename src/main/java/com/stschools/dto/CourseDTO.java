package com.stschools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private String language;
    private Integer price;
    private String image;
    private Integer itemTotal;
    private Integer videoTotal;
}
