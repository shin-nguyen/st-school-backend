package com.stschools.payload.course;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseReponse {
    @NotNull
    private Long id;
    private String name;
    private Integer price;
}
