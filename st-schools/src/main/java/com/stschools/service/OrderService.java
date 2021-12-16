package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.OrderDTO;
import com.stschools.entity.Order;
import graphql.schema.DataFetcher;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();
    OrderDTO save(OrderDTO orderDTO) throws ApiException;
//    OrderDTO save(OrderDTO orderDTO,Long id) throws ApiException;
    void deleteById(Long id);

    DataFetcher<List<Order>> findAllByCreateDateTop5();
}
