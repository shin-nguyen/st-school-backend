package com.stschools.dto;

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
    private Date createdTime;
    private Integer total;
    @JsonIgnore
    private CourseDTO courseDTO;
    @JsonIgnore
    private UserDTO userDTO;
}
