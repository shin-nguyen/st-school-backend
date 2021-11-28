package com.stschools.common.user;

import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Role;
import com.stschools.entity.User;
import com.stschools.payload.dashboard.UserResponse;
import com.stschools.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	private UserRepository repo;
	private TestEntityManager entityManager;
	
	@Autowired
	public UserRepositoryTests(UserRepository repo, TestEntityManager entityManager) {
		super();
		this.repo = repo;
		this.entityManager = entityManager;
	}
	
	
	@Test
	public void testCreateNewUserWithOneRole() {
		User user = new User();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setActive(true);
		user.setEmail("thongchuthanh2000@gmail.com");
		user.setFirstName("Cheng");
		user.setLastName("Tang Yu");
		user.setAddress("BR-VT");
		user.setPhone("0918948074");
		user.setRoles(Collections.singleton(Role.ADMIN));
		user.setProvider(AuthProvider.LOCAL);
		user.setPassword(passwordEncoder.encode("123456"));
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(System.out::println);
	}

	@Test
	public void testDashboard() {
		Pageable pageable = PageRequest.of(0, 5);

		List<UserResponse> listUsers = repo.getTopBy5(pageable).getContent();
		listUsers.forEach(System.out::println);
	}

	@Test
	public void testListAllUsersByROLE() {
		Iterable<User> listUsers = repo.findAllByRoles(Role.ADMIN);
		listUsers.forEach(System.out::println);
	}
	
	@Test
	public void testGetUserById() {
		User userById = repo.findById(1L).get();
		System.out.println(userById);
		assertThat(userById).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userUpdateUserDetails = repo.findById(1L).get();
		userUpdateUserDetails.setActive(true);
		
		repo.save(userUpdateUserDetails);
	}

	@Test
	public void testGetUserByEmail() {
		String email = "thongchuthanh2000@gmail.com";
		User userByEmail = repo.findByEmail(email);
		
		assertThat(userByEmail).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Long countById = repo.countById(1L);
		System.out.println(countById);

		assertThat(countById).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);

		List<User> listUsers = page.getContent();

		listUsers.forEach(user -> System.out.println(user));

		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	@Test
	public void testSearchUsers() {
		String keyword = "thanh";

		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);

		List<User> listUsers = page.getContent();

		listUsers.forEach(System.out::println);

		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
