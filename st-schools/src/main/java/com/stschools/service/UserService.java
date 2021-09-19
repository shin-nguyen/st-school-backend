package com.stschools.service;

import com.stschools.dto.UserDTO;
import com.stschools.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
//    List<UserDto> getCustomers();
    void addRoleToUser(String username, String roleName);

    UserDTO save(UserDTO newUser);
}
