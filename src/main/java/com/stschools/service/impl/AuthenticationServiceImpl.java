package com.stschools.service.impl;

import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.payload.auth.AuthenticationResponse;
import com.stschools.payload.common.RegistrationMobileRequest;
import com.stschools.payload.common.RegistrationRequest;
import com.stschools.repository.UserRepository;
import com.stschools.security.JwtProvider;
import com.stschools.security.oauth2.OAuth2UserInfo;
import com.stschools.service.AuthenticationService;
import com.stschools.service.MailService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final MailService mailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${hostname}")
    private String hostname;

    @Override
    @Transactional
    public AuthenticationResponse login(String email) {
        User user = userRepository.findByEmail(email);
        String userRole = user.getRoles().iterator().next().name();

        String token = jwtProvider.createToken(email, userRole);


        AuthenticationResponse response = new AuthenticationResponse();
        response.setEmail(email);
        response.setToken(token);
        response.setUserRole(userRole);
        return response;
    }

    @Override
    public boolean registerUser(RegistrationRequest registrationRequest) {
        User user = ModelMapperControl.map(registrationRequest, User.class);
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (!(userFromDb == null)) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(AuthProvider.LOCAL);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String subject = "Activation code";
        String template = "registration-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        attributes.put("registrationUrl", "http://" + hostname + "/activate/" + user.getActivationCode());
        mailSender.sendMessageHtml(user.getEmail(), subject, template, attributes);

        return true;
    }

    @Override
    public boolean registerUserMobile(RegistrationMobileRequest registrationRequest) {
        User user = ModelMapperControl.map(registrationRequest, User.class);
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (!(userFromDb == null)) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);

        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(AuthProvider.LOCAL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(AuthProvider.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public User updateOauth2User(User user, String provider, OAuth2UserInfo oAuth2UserInfo) {
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setProvider(AuthProvider.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO findByPasswordResetCode(String code) {
        return ModelMapperControl.map(userRepository.findByPasswordResetCode(code), UserDTO.class);
    }

    @Override
    public boolean sendPasswordResetCode(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        user.setPasswordResetCode(UUID.randomUUID().toString());
        userRepository.save(user);

        String subject = "Password reset";
        String template = "password-reset-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        attributes.put("resetUrl", "http://" + hostname + "/reset/" + user.getPasswordResetCode());
        mailSender.sendMessageHtml(user.getEmail(), subject, template, attributes);
        return true;
    }

    @Override
    public String passwordReset(String email, String password) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPasswordResetCode(null);
        userRepository.save(user);
        return "Password successfully changed!";
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) return false;
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
}
