package com.stschools.dto;

import com.stschools.common.enums.Catogory;
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
    private String lecturer;
    private String language;
    private String topic;
    private Integer price;
    private Integer subPrice;
    private String image;
    private Integer subTotal;
    private Integer videoTotal;
    private Catogory category;

}
