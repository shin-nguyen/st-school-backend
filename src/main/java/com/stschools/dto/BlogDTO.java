package com.stschools.dto;

import com.stschools.payload.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDTO {
    private Long id;
    @NotBlank
    private String title;
    private String summary;
    private String content;
    private String createdTime;
    private Boolean status;
    private String image;
    private Integer view;
    private String userLove;
    private Long recordLove;
    private Boolean isLove;
    private UserResponse user;
    private String topic;
}
