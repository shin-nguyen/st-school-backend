package com.stschools.component.config;

import com.stschools.common.enums.RoleType;
import com.stschools.entity.Role;
import com.stschools.entity.User;
import com.stschools.repository.RoleRepository;
import com.stschools.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        RoleRepository roleRepository){
        return args ->{

//            Role customerRole = Role.builder()
//                    .name(RoleType.ROLE_CUSTOMER.toString())
//                    .build();
//            roleRepository.saveAndFlush(customerRole);
//
//            User customer1 = User.builder()
//                    .email("kai")
//                    .password("1234")
//                    .name("this is kai")
//                    .address("HCM")
//                    .phone("03540434")
//                    .balance(10000)
//                    .role(customerRole)
//                    .status(true)
//                    .build();
//            userRepository.save(customer1);
//
//            User customer2 = User.builder()
//                    .email("sinh")
//                    .password("1234")
//                    .name("this is sinh")
//                    .address("HN")
//                    .phone("0222222")
//                    .balance(20000)
//                    .role(customerRole)
//                    .status(true)
//                    .build();
//            userRepository.save(customer2);
        };
    }
}
