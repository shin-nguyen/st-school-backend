package com.stschools.payload.course;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseRequest {
    @NotNull
    private Long id;
}
