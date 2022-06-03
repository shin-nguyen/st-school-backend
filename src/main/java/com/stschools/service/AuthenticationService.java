package com.stschools.service;

import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.payload.auth.AuthenticationResponse;
import com.stschools.payload.common.RegistrationMobileRequest;
import com.stschools.payload.common.RegistrationRequest;
import com.stschools.security.oauth2.OAuth2UserInfo;

import java.util.Map;

public interface AuthenticationService {

    AuthenticationResponse login(String email);

    boolean registerUser(RegistrationRequest user);

    boolean registerUserMobile(RegistrationMobileRequest user);

    User registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    User updateOauth2User(User user, String provider, OAuth2UserInfo oAuth2UserInfo);

    boolean activateUser(String code);

    UserDTO findByPasswordResetCode(String code);

    boolean sendPasswordResetCode(String email);

    String passwordReset(String email, String password);
}
