package com.stschools.common.quiz;


import com.stschools.entity.Quiz;
import com.stschools.entity.User;
import com.stschools.repository.QuizRepository;
import com.stschools.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false) // keep data committed into the database  (Committed transaction for test -> LOG)
public class QuizRepositoryTests {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateCurrencies() {
        User user = userRepository.findByEmail("thongchuthanh2000@gmail.com");

        List<Quiz> listQuiz = Arrays.asList(
                new Quiz("Name", "#301020", user, "10", true, 10L)
        );
        quizRepository.saveAll(listQuiz);
        Iterable<Quiz> iterable = quizRepository.findAll();
        assertThat(iterable).size().isGreaterThan(0);
    }

//    @Test
//    public void testListAllOrderByNameAsc() {
//        List<Quiz> currencies = new ArrayList<>();
//        userRepository.findAll().forEach(currencies::add);
//        currencies.forEach(System.out::println);
//        assertThat(currencies.size()).isGreaterThan(0);
//    }
}
