package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.payload.activity_progress.ActivityProgressReponse;
import com.stschools.payload.dashboard.DashboardResponse;
import com.stschools.payload.dashboard.GraphResponse;
import com.stschools.payload.dashboard.UserResponse;
import com.stschools.payload.user.UserFlutterReponse;
import com.stschools.payload.user.UserRequest;
import graphql.schema.DataFetcher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDTO findUserById(Long userId) throws ApiException;

    UserDTO findUserByEmail(String email);

    UserDTO getUserById(Long userId);

    List<UserDTO> findAllUsers();

    UserDTO updateProfile(String email, UserDTO user);

    List<UserDTO> findAllCustomers();

    DashboardResponse getDashboards();

    List<UserResponse> getAllCustomerByDashboards();

    User updateImage(String email, MultipartFile file) throws IOException;

    List<GraphResponse> dashboardGraph(Long year);

    int[] dashboardOrder(Long year);

    int[] dashboardBlog(Long year);

    UserFlutterReponse updateImageAndInfo(UserRequest userRequest, Long id) throws IOException;
}