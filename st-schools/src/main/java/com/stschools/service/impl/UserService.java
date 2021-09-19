package com.stschools.service.impl;

import com.stschools.service.IUserService;
import com.stschools.entity.Role;
import com.stschools.entity.User;
import com.stschools.repository.RoleRepository;
import com.stschools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
//        userRepository.findAll().forEach(userList::add);
        return userList;
    }

//    @Override
//    public List<UserDto> getCustomers() {
//        List<User> customers = userRepository.getCustomers();
//        return ModelMapperControl.mapAll(customers, UserDto.class);
//    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
