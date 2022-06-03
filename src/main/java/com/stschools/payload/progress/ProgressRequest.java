package com.stschools.payload.progress;

import lombok.Data;

@Data
public class ProgressRequest {
    private Long courseId;
    private Long orderId;
    private Long videoId;
}