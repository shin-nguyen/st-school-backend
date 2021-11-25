package com.stschools.dto;

import com.stschools.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

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
    private Collection<TopicDTO> topics;
    private UserDTO user;
}
