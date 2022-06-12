package com.stschools.service.impl;

import com.stschools.dto.CourseDTO;
import com.stschools.entity.Course;
import com.stschools.entity.Order;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.OrderRepository;
import com.stschools.service.CourseService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;

    @Override
    public CourseDTO findByID(Long id) {
        Course course = courseRepository.findCourseById(id);
        return ModelMapperControl.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO findByName(String name) {
        List<Course> courseList = courseRepository.findCourseByName(name);
        if (!courseList.isEmpty()) {
            return ModelMapperControl.map(courseList.get(0), CourseDTO.class);
        }
        return null;
    }

    @Override
    public List<CourseDTO> findByUserId(Long id) {
        List<Long> orders = orderRepository.findOrderByUserId(id).stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        List<Course> courses = courseRepository.findAll().stream()
                .filter(course -> orders.contains(course.getId()))
                .collect(Collectors.toList());

        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCoursesByAdmin() {
        List<Course> courses = courseRepository.findAll();
        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getPromotionCourses() {
        List<Course> courses = courseRepository.findAll().stream().filter(
                course -> (course.getPrice() > course.getSubPrice() && course.getSubPrice() > 0)
        ).collect(Collectors.toList());

        return ModelMapperControl.mapAll(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCourses(Long id) {
        List<Long> orders = orderRepository.findOrderByUserId(id).stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        List<Course> courses = courseRepository.findAll().stream()
                .filter(course -> !orders.contains(course.getId()))
                .collect(Collectors.toList());

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
