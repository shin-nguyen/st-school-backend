package com.stschools.service.impl;

import com.stschools.dto.OrderDTO;
import com.stschools.dto.ProgressDTO;
import com.stschools.entity.Course;
import com.stschools.entity.Order;
import com.stschools.entity.Video;
import com.stschools.payload.course.CourseRequest;
import com.stschools.payload.orders.OrderReponse;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.OrderRepository;
import com.stschools.repository.UserRepository;
import com.stschools.repository.VideoRepository;
import com.stschools.service.OrderService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.stschools.service.MailService;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final VideoRepository videoRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final MailService mailSender;

    @Override
    public List<OrderDTO> getAll() {
        return ModelMapperControl.mapAll(orderRepository.findAll(), OrderDTO.class);
    }
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Course course = courseRepository.findCourseById(orderDTO.getCourse().getId());
        course.setSubTotal(course.getSubTotal()+1);
        courseRepository.saveAndFlush(course);
        Order order = ModelMapperControl.map(orderDTO, Order.class);
        order.setCourse(course);
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

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderReponse> findAllByCreateDateTop5() {
        Page<Order> orders = orderRepository.findByTop(PageRequest.of(0, 5));
        return ModelMapperControl.mapAll(orders.getContent(), OrderReponse.class);
    }

    @Override
    public Long countByCourseId(Long id) {
        return orderRepository.countByCourseId(id);
    }

    @Override
    public void updateProgress(ProgressDTO progressDTO) {
        Order order = progressDTO.getOrderId() == null ?
                orderRepository.findOrderByCourseIdAndUserId(progressDTO.getCourseId(), progressDTO.getUserId())
                : orderRepository.getById(progressDTO.getOrderId());
        List<Video> listVideo = videoRepository.findByCourse_Id(progressDTO.getCourseId());
        if(order != null && !order.getVideos().contains(progressDTO.getVideo())) {
            Set<Video> videos = order.getVideos();
            Video video = videoRepository.findVideoById(progressDTO.getVideo().getId());
            if(video != null) {
                videos.add(video);
                order.setVideos(videos);
            }
        }
        order.setProgress((double) Math.round((double)order.getVideos().size()/ listVideo.size()*100));
        orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderByUserAndCourse(Long userId, Long courseId) {
        return ModelMapperControl.map(orderRepository.findOrderByCourseIdAndUserId(courseId, userId), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrderByUser(Long userId) {
        return ModelMapperControl.mapAll(orderRepository.findOrderByUserId(userId), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> saveAll(List<CourseRequest> courses, Long userId) {
        List<Order> orders= new ArrayList<>();
        for (CourseRequest course :courses) {
            Course courseNew = courseRepository.findCourseById(course.getId());
            courseNew.setSubTotal(courseNew.getSubTotal()+1);
            courseRepository.saveAndFlush(courseNew);
            Order order  =new Order();
            order.setUser(userRepository.findById(userId).get());
            order.setCourse(courseNew);
            orders.add(order);
        }
        orders = orderRepository.saveAll(orders);

//        try{
//            String subject = "Order #" + order.getId();
//            String template = "order-template";
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("order", newOrder);
//            mailSender.sendMessageHtml(order.getUser().getEmail(), subject, template, attributes);
//        } catch (Exception ignored){
//
//        }

        return ModelMapperControl.mapAll(orders, OrderDTO.class);
    }
}
