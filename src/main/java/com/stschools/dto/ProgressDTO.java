package com.stschools.dto;

import lombok.Data;

@Data
public class ProgressDTO {
    private Long courseId;
    private Long userId;
    private Long orderId;
    private VideoDTO video;
}
