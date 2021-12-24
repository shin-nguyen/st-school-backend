package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.entity.User;
import com.stschools.payload.dashboard.DashboardResponse;
import com.stschools.payload.dashboard.GraphResponse;
import com.stschools.payload.dashboard.UserResponse;
import graphql.schema.DataFetcher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User findUserById(Long userId) throws ApiException;

    User findUserByEmail(String email);

    DataFetcher<List<User>> getAllUsersByQuery();

    DataFetcher<User> getUserByQuery();

    List<User> findAllUsers();

    User updateProfile(String email, User user);

    List<User> findAllCustomers();

    DashboardResponse getDashboards();

    List<UserResponse> getAllCustomerByDashboards();

    User updateImage(String email, MultipartFile file) throws IOException;

    List<GraphResponse> dashboardGraph(Long year);

    int[] dashboardOrder(Long year);
    int[] dashboardBlog(Long year);
}