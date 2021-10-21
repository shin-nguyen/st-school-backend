package com.stschools.service.impl;

import com.stschools.dto.OrderDTO;
import com.stschools.entity.Order;
import com.stschools.repository.OrderRepository;
import com.stschools.service.OrderService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAll() {

        return ModelMapperControl.mapAll(orderRepository.findAll(), OrderDTO.class);
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = ModelMapperControl.map(orderDTO, Order.class);
        return ModelMapperControl.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
