//package com.stschools.common.user;
//
//import com.cloudinary.api.exceptions.ApiException;
//import com.stschools.common.enums.Role;
//import com.stschools.entity.Blog;
//import com.stschools.entity.User;
//import com.stschools.import_file.blogs.BlogExcelImporter;
//import com.stschools.repository.UserRepository;
//import com.stschools.service.impl.VideoServiceImpl.UserServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserServiceTest {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    BlogExcelImporter blogExcelImporter;
//
//    @Test
//    public void findUserById() throws ApiException {
//        User user = new User();
//        user.setId(1L);
//
//        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
//        userService.findUserById(1L);
//        assertEquals(1L, user.getId());
//        verify(userRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void findUserByEmail() {
//        String USER_EMAIL = "thongchuthanh2000@gmail.com";
//        User user = new User();
//        user.setEmail(USER_EMAIL);
//        userService.findUserByEmail(USER_EMAIL);
//
//        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
//        assertEquals(USER_EMAIL, user.getEmail());
//        verify(userRepository, times(1)).findByEmail(USER_EMAIL);
//    }
//
//    @Test
//    public void findAllUsers() {
//        List<User> usersList = new ArrayList<>();
//        usersList.add(new User());
//        usersList.add(new User());
//        userService.findAllUsers();
//
//        when(userRepository.findAllByOrderByIdAsc()).thenReturn(usersList);
//        assertEquals(2, usersList.size());
//        verify(userRepository, times(1)).findAllByOrderByIdAsc();
//    }
//
//    @Test
//    public void loadUserByUsername() {
//        String USER_EMAIL = "thongchuthanh2000@gmail.com";
//        String FIRST_NAME = "Thành";
//
//        User user = new User();
//        user.setEmail(USER_EMAIL);
//        user.setActive(true);
//        user.setFirstName(FIRST_NAME);
//        user.setRoles(Collections.singleton(Role.ADMIN));
//
//        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
//        assertEquals(USER_EMAIL, user.getEmail());
//        assertEquals(FIRST_NAME, user.getFirstName());
//        assertTrue(user.isActive());
//    }
//
//    @Test
//    public void updateProfile() {
//        String USER_EMAIL = "thongchuthanh2000@gmail.com";
//        String FIRST_NAME = "Thành";
//
//        User user = new User();
//        user.setEmail(USER_EMAIL);
//        user.setFirstName(FIRST_NAME);
//
//        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);
//        when(userRepository.save(user)).thenReturn(user);
////        userService.updateProfile(USER_EMAIL, user);
//        assertEquals(USER_EMAIL, user.getEmail());
//        assertEquals(FIRST_NAME, user.getFirstName());
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//        verify(userRepository, times(1)).save(user);
//    }
//
//}
