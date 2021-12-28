package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.common.enums.Role;
import com.stschools.entity.Blog;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.payload.dashboard.DashboardResponse;
import com.stschools.payload.dashboard.GraphResponse;
import com.stschools.payload.dashboard.UserResponse;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.OrderRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.BlogService;
import com.stschools.service.OrderService;
import com.stschools.service.UserService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;
    private final BlogRepository blogRepository;
    private final Cloudinary cloudinary;

    @Override
    public User findUserById(Long userId) throws ApiException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new ApiException("Could not find  blog with ID ");
        }
        return user.get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<User> getUserByQuery() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return userRepository.findById(userId).get();
        };
    }

    @Override
    public DataFetcher<List<User>> getAllUsersByQuery() {
        return dataFetchingEnvironment -> userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public User updateProfile(String email, User user) {
        User userFromDb = userRepository.findByEmail(email);
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhone(user.getPhone());
        userRepository.save(userFromDb);
        return userFromDb;
    }

    @Override
    public List<User> findAllCustomers() {
        return userRepository.findAlLByRoles(Role.USER);
    }

    @Override
    public DashboardResponse getDashboards() {
        DashboardResponse dashboardResponse = DashboardResponse.builder()
                .totalBlog(blogRepository.count())
                .totalOrder(orderRepository.count())
                .totalCourse(courseRepository.count())
                .totalIncome(orderRepository.getSumImcome())
                .build();

      return dashboardResponse;
    }

    @Override
    public List<UserResponse> getAllCustomerByDashboards() {
        Page<UserResponse> orders = userRepository.getTopBy5(PageRequest.of(0, 5));
        return orders.getContent();
    }

    @Override
    public User updateImage(String email, MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + file.getOriginalFilename()));

        User user = userRepository.findByEmail(email);
        user.setAvatar(uploadResult.get("secure_url").toString());
        return userRepository.save(user);
    }

    @Override
    public int[] dashboardOrder(Long year) {
        int[] frequency =  IntStream.of(0,0,0,0,0,0,0,0,0,0,0,0).toArray();
        List<Order> orders = orderRepository.findAll();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        orders.forEach(order -> {
            Date startDate = null;
            try {
                startDate = df.parse(order.getCreatedTime());
                frequency[startDate.getMonth()]++;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return frequency;
    }

    @Override
    public int[] dashboardBlog(Long year) {
        int[] frequency =  IntStream.of(0,0,0,0,0,0,0,0,0,0,0,0).toArray();
        List<Blog> blogs = blogRepository.findAll();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        blogs.forEach(blog -> {
            Date startDate = null;
            try {
                startDate = df.parse(blog.getCreatedTime());
                frequency[startDate.getMonth()]++;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return frequency;
    }

    @Override
    public List<GraphResponse> dashboardGraph(Long year) {
        List<GraphResponse> list = new ArrayList<>();

        GraphResponse orderGraph = new GraphResponse("Order",dashboardOrder(year));
        GraphResponse blogGraph = new GraphResponse("Blog",dashboardBlog(year));
        list.add(orderGraph);
        list.add(blogGraph);
        return list;
    }
}
