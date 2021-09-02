package com.stschools.service;

import com.stschools.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getCourses();
    CourseDto save(CourseDto courseDto);
    void deleteById(Long id);

    CourseDto update(CourseDto course);
}
