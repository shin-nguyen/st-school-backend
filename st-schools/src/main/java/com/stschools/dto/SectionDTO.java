package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stschools.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private Course course;
}
