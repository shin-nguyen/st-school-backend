package com.stschools.common.user;

import com.stschools.entity.Role;
import com.stschools.entity.User;
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
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

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
		Role roleAdmin = entityManager.find(Role.class, 1L);
		LocalDate date = LocalDate.of(2000, 11, 12);

		User userWithOneRole = new User("tangyucheng","thongchuthanh2000@gmail.com",
				"$2a$12$5EPUJpJxWREx.dqp27Kx3.ezavcM1VXWpvJ.4a3s7aimPOIJruFIO",
				"thongchuthanh","defaults",date,"BR-VT","0918948074",true);
		userWithOneRole.getRoles().add(roleAdmin);

		User savedUser = repo.save(userWithOneRole);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
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
		userUpdateUserDetails.setStatus(true);
		
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
