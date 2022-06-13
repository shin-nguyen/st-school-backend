package com.stschools.service.impl;

import com.stschools.dto.BlogDTO;
import com.stschools.dto.CourseDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.Course;
import com.stschools.entity.Order;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.OrderRepository;
import com.stschools.service.CourseService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;

    @Override
    public CourseDTO findByID(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Course is null!", HttpStatus.BAD_REQUEST));
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
    public List<CourseDTO> getTopNew(Long userId) {
        List<Course> courseList = (userId == null)? courseRepository.findAll() : ModelMapperControl.mapAll(this.getCourses(userId), Course.class);
        return ModelMapperControl.mapAll(courseList
                        .stream()
                        .sorted(Comparator.comparing(Course::getId, Comparator.comparing(Math::abs)).reversed())
                        .limit(10)
                        .collect(Collectors.toList())
        , CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getTopHot(Long userId) {
        List<Course> courseList = (userId == null)? courseRepository.findAll() : ModelMapperControl.mapAll(this.getCourses(userId), Course.class);
        return ModelMapperControl.mapAll(courseList
                        .stream()
                        .sorted(Comparator.comparing(Course::getSubTotal, Comparator.comparing(Math::abs)).reversed())
                        .limit(10)
                        .collect(Collectors.toList())
                , CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCourses(Long id) {
        List<Course> courseList = orderRepository.findOrderByUserId(id).stream()
                .map(Order::getCourse)
                .collect(Collectors.toList());

        List<Course> courses = courseRepository.findAll().stream()
                .filter(course -> !courseList.contains(course))
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
