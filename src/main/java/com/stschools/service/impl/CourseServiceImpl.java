package com.stschools.service.impl;

import com.stschools.dto.CourseDTO;
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
    public CourseDTO findByID(Long id) {
        Course course = courseRepository.findCourseById(id);
        return ModelMapperControl.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO findByName(String name) {
        List<Course> courseList = courseRepository.findCourseByName(name);
        if(!courseList.isEmpty()){
            return ModelMapperControl.map(courseList.get(0), CourseDTO.class);
        }
        return null;
    }

    @Override
    public List<CourseDTO> findByUserId(Long id) {
        List<Course> courseList = courseRepository.findCoursesByUserId(id);
        return ModelMapperControl.mapAll(courseList, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCoursesByAdmin() {
        List<Course> courses = courseRepository.findAll();
        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getPromotionCourses() {
        List<Course> courses = courseRepository.findPromotionCourses();
        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCourses(Long id) {
        List<Course> courses = courseRepository.findCoursesByNotInOrder(id);
        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public CourseDTO save(CourseDTO courseDto) {
        Course course = ModelMapperControl.map(courseDto, Course.class);
        return ModelMapperControl.map(courseRepository.save(course), CourseDTO.class);
    }

    @Override
    public CourseDTO update(CourseDTO courseDto) {
        Course course = courseRepository.findCourseById(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setLanguage(courseDto.getLanguage());
        course.setPrice(courseDto.getPrice());
        course.setImage(courseDto.getImage());
        return ModelMapperControl.map(courseRepository.save(course), CourseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }


}
