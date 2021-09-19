package com.stschools.service;

import com.stschools.dto.UserDto;
import com.stschools.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
//    List<UserDto> getCustomers();
    void addRoleToUser(String username, String roleName);
}
