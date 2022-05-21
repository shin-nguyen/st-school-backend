package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CourseDTO;
import com.stschools.dto.OrderDTO;
import com.stschools.dto.ProgressDTO;
import com.stschools.dto.UserDTO;
import com.stschools.entity.Order;
import graphql.schema.DataFetcher;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();
    OrderDTO save(OrderDTO orderDTO) throws ApiException;
//    OrderDTO save(OrderDTO orderDTO,Long id) throws ApiException;
    void deleteById(Long id);

    DataFetcher<List<Order>> findAllByCreateDateTop5();

    Long countByCourseId(Long id);

    void updateProgress(ProgressDTO progressDTO);

    OrderDTO getOrderByUserAndCourse(Long userId, Long courseId);

    List<OrderDTO> getOrderByUser(Long userId);

    List<OrderDTO> saveAll(List<CourseDTO> course, UserDTO userDTO);
}
