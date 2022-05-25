package com.stschools.payload.orders;

import com.stschools.payload.course.CourseRequest;
import lombok.Data;
import java.util.List;

@Data
public class OrdersRequest {
    private  List<CourseRequest> courses;
}
