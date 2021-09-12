package com.stschools.service.impl;

import com.stschools.dto.CourseDto;
import com.stschools.entity.Course;
import com.stschools.repository.CourseRepository;
import com.stschools.service.CourseService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDto findByID(Long id) {
        Course course = courseRepository.findCourseById(id);
        return ModelMapperControl.map(course, CourseDto.class);
    }           

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return ModelMapperControl.mapAll(courses, CourseDto.class);
    }

    @Override
    public CourseDto save(CourseDto courseDto) {
        Course course = ModelMapperControl.map(courseDto, Course.class);
        return ModelMapperControl.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public CourseDto update(CourseDto courseDto) {
        Course course = courseRepository.findCourseById(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setImage(courseDto.getImage());
        return ModelMapperControl.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
