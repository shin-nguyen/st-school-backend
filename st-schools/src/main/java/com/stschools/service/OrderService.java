package com.stschools.service;

import com.stschools.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();
    OrderDTO save(OrderDTO orderDTO);
    void deleteById(Long id);
}
