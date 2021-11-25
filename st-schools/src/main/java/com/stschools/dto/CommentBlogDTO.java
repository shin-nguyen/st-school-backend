package com.stschools.dto;

import com.stschools.entity.Blog;
import com.stschools.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CommentBlogDTO {
    private Long id;

    private String createdTime;
    private String updateTime;

    @NotBlank
    private String content;

    private UserDTO user;

    private BlogDTO blog;
}
