package com.stschools.service.impl;

import com.stschools.util.ModelMapperControl;
import com.stschools.dto.UserDto;
import com.stschools.entity.Role;
import com.stschools.entity.User;
import com.stschools.repository.RoleRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDto> getCustomers() {
        List<User> customers = userRepository.getCustomers();
        return ModelMapperControl.mapAll(customers, UserDto.class);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);
    }
}
