package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.common.enums.Role;
import com.stschools.dto.UserDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.payload.activity_progress.ActivityProgressReponse;
import com.stschools.payload.dashboard.DashboardResponse;
import com.stschools.payload.dashboard.GraphResponse;
import com.stschools.payload.dashboard.UserResponse;
import com.stschools.payload.user.UserFlutterReponse;
import com.stschools.payload.user.UserRequest;
import com.stschools.repository.*;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    public UserDTO findUserById(Long userId) throws ApiException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return ModelMapperControl.map(userRepository.findByEmail(email), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return ModelMapperControl.mapAll(userRepository.findAllByOrderByIdAsc(), UserDTO.class);
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
    public UserDTO updateProfile(String email, UserDTO user) {
        User userFromDb = userRepository.findByEmail(email);

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhone(user.getPhone());
        userFromDb.setAbout(user.getAbout());
        userRepository.save(userFromDb);

        return ModelMapperControl.map(userFromDb, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllCustomers() {
        return ModelMapperControl.mapAll(userRepository.findAlLByRoles(Role.USER), UserDTO.class);
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
        int[] frequency = IntStream.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).toArray();
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
        int[] frequency = IntStream.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).toArray();
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
    public UserFlutterReponse updateImageAndInfo(UserRequest userRequest, Long id) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(userRequest.getAvatar().getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + userRequest.getAvatar().getOriginalFilename()));

        User userFromDb = userRepository.findById(id).orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        ;

        userFromDb.setAvatar(uploadResult.get("secure_url").toString());
        userFromDb.setFirstName(userRequest.getFirstName());
        userFromDb.setLastName(userRequest.getLastName());
        userFromDb.setAddress(userRequest.getAddress());
        userFromDb.setPhone(userRequest.getPhone());
        userFromDb.setAbout(userRequest.getAbout());
        return ModelMapperControl.map(userRepository.save(userFromDb), UserFlutterReponse.class);
    }


    @Override
    public List<GraphResponse> dashboardGraph(Long year) {
        List<GraphResponse> list = new ArrayList<>();

        GraphResponse orderGraph = new GraphResponse("Order", dashboardOrder(year));
        GraphResponse blogGraph = new GraphResponse("Blog", dashboardBlog(year));
        list.add(orderGraph);
        list.add(blogGraph);
        return list;
    }
}
