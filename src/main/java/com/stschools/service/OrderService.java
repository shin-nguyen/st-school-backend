package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.OrderDTO;
import com.stschools.dto.ProgressDTO;
import com.stschools.entity.Order;
import com.stschools.payload.course.CourseRequest;
import com.stschools.payload.orders.OrderReponse;
import graphql.schema.DataFetcher;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();

    OrderDTO save(OrderDTO orderDTO) throws ApiException;

    void deleteById(Long id);

    List<OrderReponse> findAllByCreateDateTop5();

    Long countByCourseId(Long id);

    void updateProgress(ProgressDTO progressDTO);

    OrderDTO getOrderByUserAndCourse(Long userId, Long courseId);

    List<OrderDTO> getOrderByUser(Long userId);

    List<OrderDTO> saveAll(List<CourseRequest> course, Long userId);
}
