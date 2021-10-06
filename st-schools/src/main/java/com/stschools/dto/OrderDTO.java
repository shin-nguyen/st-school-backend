package com.stschools.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdTime;
    private Integer total;
    private CourseDTO course;
    private UserDTO user;

    public void setTotal() {
        this.total = course.getPrice();
    }

    public Integer getTotal() {
        return course.getPrice();
    }

    public void setCreatedTime() {
        this.createdTime = new Date();
    }
}
