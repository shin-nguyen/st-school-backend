package com.stschools.service;

import com.stschools.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getCourses(Long id);

    CourseDTO save(CourseDTO courseDto);

    void deleteById(Long id);

    CourseDTO update(CourseDTO course);

    CourseDTO findByID(Long id);

    CourseDTO findByName(String name);

    List<CourseDTO> findByUserId(Long id);

    List<CourseDTO> getCoursesByAdmin();

    List<CourseDTO> getPromotionCourses();
}
