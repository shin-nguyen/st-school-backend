package com.stschools.mapper;

import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.payload.auth.AuthenticationResponse;
import com.stschools.payload.common.RegistrationRequest;
import com.stschools.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public UserDTO findByPasswordResetCode(String code) {
        return userMapper.convertToResponseDto(authenticationService.findByPasswordResetCode(code));
    }

    public AuthenticationResponse login(String email) {
        Map<String, String> resultMap = authenticationService.login(email);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setEmail(resultMap.get("email"));
        response.setToken(resultMap.get("token"));
        response.setUserRole(resultMap.get("userRole"));
        return response;
    }

    public boolean registerUser(RegistrationRequest registrationRequest) {
        User user = userMapper.convertToEntity(registrationRequest);
        return authenticationService.registerUser(user);
    }

    public boolean activateUser(String code) {
        return authenticationService.activateUser(code);
    }

    public boolean sendPasswordResetCode(String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    public String passwordReset(String email, String password) {
        return authenticationService.passwordReset(email, password);
    }
}
