package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stschools.entity.Course;
import com.stschools.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private CourseDTO course;

    private Collection<LectureDTO> lectures;
}
