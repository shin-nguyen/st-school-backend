package com.stschools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDto {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private Date timeCreated;
    private Boolean status;
    private TopicDto topic;
    private UserDto user;
}
