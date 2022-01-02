package com.stschools.dto;

import com.stschools.entity.Course;
import com.stschools.entity.User;
import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String createdTime;
    private Integer rate;
    private String content;
    private User user;
    private Course course;

}
