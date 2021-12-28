package com.stschools.payload.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {
    private Long totalCourse;
    private Long totalOrder;
    private Long totalIncome;
    private Long totalBlog;
}
