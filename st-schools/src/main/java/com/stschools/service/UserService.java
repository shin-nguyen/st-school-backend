package com.stschools.service;

import com.stschools.entity.User;
import com.stschools.payload.dashboard.UserReponse;
import graphql.schema.DataFetcher;

import java.util.List;

public interface UserService {

    User findUserById(Long userId);

    User findUserByEmail(String email);

    DataFetcher<List<User>> getAllUsersByQuery();

    DataFetcher<User> getUserByQuery();

    List<User> findAllUsers();

    User updateProfile(String email, User user);

    List<User> findAllCustomers();

    DataFetcher<List<UserReponse>> getAllByDashboards();
}