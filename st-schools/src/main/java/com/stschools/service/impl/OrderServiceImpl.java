package com.stschools.service.impl;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.OrderDTO;
import com.stschools.dto.UserDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.repository.OrderRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.OrderService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.stschools.service.MailService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final UserService userService;

    private final MailService mailSender;

    @Override
    public List<OrderDTO> getAll() {
        return ModelMapperControl.mapAll(orderRepository.findAll(), OrderDTO.class);
    }
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = ModelMapperControl.map(orderDTO, Order.class);
//        return ModelMapperControl.map(orderRepository.save(order), OrderDTO.class);
        Order newOrder = orderRepository.save(order);

        try{
            String subject = "Order #" + order.getId();
            String template = "order-template";
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("order", newOrder);
            mailSender.sendMessageHtml(order.getUser().getEmail(), subject, template, attributes);
        } catch (Exception ignored){

        }

        return ModelMapperControl.map(newOrder, OrderDTO.class);
    }

//    @Override
//    public OrderDTO save(OrderDTO orderDTO) throws ApiException {
//        Order order = ModelMapperControl.map(orderDTO, Order.class);
//
//        if (orderRepository.countByCourseIdAndUserId(order.getCourse().getId(),user.getId())!=0){
//            throw new ApiException("Could not add Order");
//        }
//
//        Order newOrder = orderRepository.save(order);
//
//        try{
//            String subject = "Order #" + order.getId();
//            String template = "order-template";
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("order", newOrder);
//            mailSender.sendMessageHtml(order.getUser().getEmail(), subject, template, attributes);
//        } catch (Exception ignored){
//
//        }
//
//        return ModelMapperControl.map(newOrder, OrderDTO.class);
//    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public DataFetcher<List<Order>> findAllByCreateDateTop5() {
        Page<Order> orders = orderRepository.findByTop(PageRequest.of(0, 5));
        return dataFetchingEnvironment -> orders.getContent();
    }


}
