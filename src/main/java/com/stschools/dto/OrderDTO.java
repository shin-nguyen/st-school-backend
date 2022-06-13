package com.stschools.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;

    @JsonProperty("vnpay_id")
    private String vnpayId;

    private String createdTime;
    private String updateTime;
    private Integer total;
    private Double progress;
    private CourseDTO course;
    private UserDTO user;
    private Boolean isComplete;
    private Set<VideoDTO> videos;
}
