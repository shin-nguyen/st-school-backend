package com.stschools.dto;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDTO {
    private Long id;
    private String name;
    private String source;
    private Long duration;
    private CourseDTO course;

//    @JsonGetter
//    public Long getidCourse() {
//        return course.getId();
//    }
}
