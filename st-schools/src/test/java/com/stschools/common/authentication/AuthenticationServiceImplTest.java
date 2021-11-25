package com.stschools.common.authentication;


import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import com.stschools.entity.User;
import com.stschools.repository.UserRepository;
import com.stschools.security.JwtProvider;
import com.stschools.security.oauth2.GoogleOAuth2UserInfo;
import com.stschools.security.oauth2.OAuth2UserFactory;
import com.stschools.security.oauth2.OAuth2UserInfo;
import com.stschools.service.impl.AuthenticationServiceImpl;

import com.stschools.service.impl.MailServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private MailServiceImpl mailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private String hostname = "3000";

//    @Test
//    public void findByPasswordResetCode() {
//        User user = new User();
//        user.setPasswordResetCode(USER_PASSWORD_RESET_CODE);
//        authenticationService.findByPasswordResetCode(USER_PASSWORD_RESET_CODE);
//
//        when(userRepository.findByPasswordResetCode(USER_PASSWORD_RESET_CODE)).thenReturn(user);
//        assertEquals(USER_PASSWORD_RESET_CODE, user.getPasswordResetCode());
//        verify(userRepository, times(1)).findByPasswordResetCode(USER_PASSWORD_RESET_CODE);
//    }

    @Test
    public void login() {
        String USER_EMAIL = "thongchuthanh2000@gmail.com";
        String FIRST_NAME = "Thành";

        User user = new User();
        user.setId(1L);
        user.setEmail(USER_EMAIL);
        user.setActive(true);
        user.setFirstName(FIRST_NAME);
        user.setRoles(Collections.singleton(Role.ADMIN));

        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
        assertEquals(1L, user.getId());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(FIRST_NAME, user.getFirstName());
        authenticationService.login(USER_EMAIL);
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(jwtProvider, times(1)).createToken(user.getEmail(), user.getRoles().iterator().next().name());
    }

    @Test
    public void registerUser() {
        User user = new User();
        String FIRST_NAME = "Cheng";
        String USER_EMAIL = "18110365@student.hcmute.edu.vn";
        String PASSWORD = "123456";

        user.setFirstName(FIRST_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(PASSWORD);
        boolean isUserCreated = authenticationService.registerUser(user);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", FIRST_NAME);
        attributes.put("registrationUrl", "http://" + hostname + "/activate/" + user.getActivationCode());

        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void registerGoogleOauthUser() {
        String FIRST_NAME = "Cheng";
        String LAST_NAME = "Tang Yu";

        String USER_EMAIL = "18110365@student.hcmute.edu.vn";

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", 123456);
        attributes.put("given_name", FIRST_NAME);
        attributes.put("family_name", LAST_NAME);
        attributes.put("email", USER_EMAIL);
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(attributes);
        OAuth2UserInfo google = OAuth2UserFactory.getOAuth2UserInfo("google", attributes);

        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(AuthProvider.GOOGLE);

        when(userRepository.save(user)).thenReturn(user);
        authenticationService.registerOauth2User("google", userInfo);
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(google.getAttributes(), userInfo.getAttributes());
        assertNull(user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void sendPasswordResetCode() {
        User user = new User();
        String USER_PASSWORD_RESET_CODE = "58ac185a-e4ef-4cdd-95e3-061fc48b6dd2";
        String USER_EMAIL = "18110365@student.hcmute.edu.vn";

        user.setEmail(USER_EMAIL);
        user.setPasswordResetCode(USER_PASSWORD_RESET_CODE);

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
        authenticationService.sendPasswordResetCode(USER_EMAIL);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", null);
        attributes.put("resetUrl", "http://" + hostname + "/reset/" + user.getPasswordResetCode());

        assertEquals(USER_EMAIL, user.getEmail());
        assertNotNull(user.getPasswordResetCode());
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findByEmail(user.getEmail());

    }

    @Test
    public void passwordReset() {
        User user = new User();

        String USER_PASSWORD = "123456";
        String USER_EMAIL = "18110365@student.hcmute.edu.vn";

        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);

        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
        when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(user.getPassword());
        when(userRepository.save(user)).thenReturn(user);
        authenticationService.passwordReset(user.getEmail(), user.getPassword());
        assertEquals(USER_EMAIL, user.getEmail());
        assertNotNull(user.getPassword());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void activateUser() {
        User user = new User();
        String USER_ACTIVATION_CODE = "58ac185a-e4ef-4cdd-95e3-061fc48b6dd2";
        user.setActivationCode(USER_ACTIVATION_CODE);

        when(userRepository.findByActivationCode(USER_ACTIVATION_CODE)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        boolean isActivated = authenticationService.activateUser(user.getActivationCode());
        assertTrue(isActivated);
        assertNull(user.getActivationCode());
        verify(userRepository, times(1)).save(user);
    }
}
