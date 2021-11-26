package com.stschools.mapper;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.payload.common.RegistrationRequest;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import com.stschools.util.ObjectMapperControl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserService userService;

    private User convertToEntity(UserDTO userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    User convertToEntity(RegistrationRequest registrationRequest) {
        return ModelMapperControl.map(registrationRequest, User.class) ;
    }

    UserDTO convertToResponseDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO findUserById(Long userId) throws ApiException {
        return convertToResponseDto(userService.findUserById(userId));
    }

    public UserDTO findUserByEmail(String email) {
        return convertToResponseDto(userService.findUserByEmail(email));
    }


    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findAllCustomers() {
        return userService.findAllCustomers()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public UserDTO updateProfile(String email, UserDTO userRequest) {
        return convertToResponseDto(userService.updateProfile(email, convertToEntity(userRequest)));
    }

}
