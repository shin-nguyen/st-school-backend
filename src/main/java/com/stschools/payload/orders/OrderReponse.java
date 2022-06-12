package com.stschools.payload.orders;


import com.stschools.payload.course.CourseReponse;
import com.stschools.payload.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReponse {
    private Long id;
    private String createdTime;
    private CourseReponse course;
    private UserResponse user;
}
