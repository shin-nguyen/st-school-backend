package com.stschools.service.impl;

import com.stschools.dto.UserDTO;
import com.stschools.exceptions.UsernameAlreadyExistsException;
import com.stschools.service.UserService;
import com.stschools.entity.Role;
import com.stschools.entity.User;
import com.stschools.repository.RoleRepository;
import com.stschools.repository.UserRepository;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = ModelMapperControl.map(userDTO, User.class);
        try {
            return ModelMapperControl.map(userRepository.save(user), UserDTO.class);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
    }
}
