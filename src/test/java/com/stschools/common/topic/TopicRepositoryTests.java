package com.stschools.common.topic;


import com.stschools.entity.Topic;
import com.stschools.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false) // keep data committed into the database  (Committed transaction for test -> LOG)
public class TopicRepositoryTests {

	@Autowired
	private TopicRepository repo;
	@Test
	public void testCreateCurrencies() {
		List<Topic> listTopic = Arrays.asList(
			new Topic("JAVA SCRIPT", "Java script"),
			new Topic("SQL", "Sql"),
			new Topic("HTML/CSS", "Html & Css"),
			new Topic("JAVA", "Java"));

		repo.saveAll(listTopic);

		Iterable<Topic> iterable = repo.findAll();

		assertThat(iterable).size().isEqualTo(4);
	}
	
	@Test
	public void testListAllOrderByNameAsc() {
		List<Topic> currencies = new ArrayList<>();
		repo.findAll().forEach(currencies::add);

		currencies.forEach(System.out::println);

		assertThat(currencies.size()).isGreaterThan(0);
	}
}
