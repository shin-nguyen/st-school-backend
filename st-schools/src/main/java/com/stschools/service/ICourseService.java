package com.stschools.service;

import com.stschools.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    List<CourseDto> getCourses();
    CourseDto save(CourseDto courseDto);
    void deleteById(Long id);

    CourseDto update(CourseDto course);
    CourseDto findByID(Long id);
}
