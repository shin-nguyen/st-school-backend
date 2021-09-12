package com.stschools.component.config;

import com.stschools.common.enums.RoleType;
import com.stschools.entity.Role;
import com.stschools.entity.Topic;
import com.stschools.entity.User;
import com.stschools.repository.RoleRepository;
import com.stschools.repository.TopicRepository;
import com.stschools.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class InitData {

    @Bean
    CommandLineRunner commandLineRunner(TopicRepository topicRepository,
                                        UserRepository userRepository,
                                        RoleRepository roleRepository){
        return args ->{
//            Topic js = Topic.builder()
//                    .name("JAVA SCRIPT")
//                    .description("Java script")
//                    .build();
//            Topic sql = Topic.builder()
//                    .name("SQL")
//                    .description("Sql")
//                    .build();
//            Topic html_cc = Topic.builder()
//                    .name("HTML/CSS")
//                    .description("Html & Css")
//                    .build();
//            Topic java = Topic.builder()
//                    .name("JAVA")
//                    .description("Java")
//                    .build();
//
//            List<Topic> topics = new ArrayList<>();
//            topics.add(js);
//            topics.add(sql);
//            topics.add(html_cc);
//            topics.add(java);
//
//            topicRepository.saveAll(topics);
//
//            Role customerRole = Role.builder()
//                    .name(RoleType.ROLE_CUSTOMER.toString())
//                    .build();
//            Role adminRole = Role.builder()
//                    .name(RoleType.ROLE_ADMIN.toString())
//                    .build();
//
//            List<Role> roles = new ArrayList<>();
//            roles.add(adminRole);
//            roles.add(customerRole);
//
//            roleRepository.saveAll(roles);
//
//            User admin = User.builder()
//                    .email("admin@gmail.com")
//                    .password("1234")
//                    .name("Admin")
//                    .avatar("default.jpg")
//                    .address("HCM")
//                    .phone("03540434")
//                    .dateOfBirth(LocalDate.of(2000, Month.FEBRUARY,27))
//                    .role(adminRole)
//                    .errorNumber(0)
//                    .status(true)
//                    .build();
//            User customer = User.builder()
//                    .email("user@gmail.com")
//                    .password("1234")
//                    .name("User")
//                    .avatar("default.jpg")
//                    .address("HN")
//                    .phone("0222222")
//                    .dateOfBirth(LocalDate.of(2000, Month.AUGUST,8))
//                    .role(customerRole)
//                    .errorNumber(0)
//                    .status(true)
//                    .build();
//
//            List<User> users = new ArrayList<>();
//            users.add(admin);
//            users.add(customer);
//
//            userRepository.saveAll(users);
        };
    }
}
