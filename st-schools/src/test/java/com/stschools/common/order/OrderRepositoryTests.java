package com.stschools.common.order;

import com.stschools.entity.Course;
import com.stschools.entity.Order;
import com.stschools.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testDashboard() {
        Page<Order> orders = orderRepository.findByTop(PageRequest.of(0, 5));
        List<Order> list = orders.getContent();
        list.forEach(System.out::println);
    }

    @Test
    public void findIncome() {
        Long sum = orderRepository.getSumImcome();
        System.out.println(sum);
        assertThat(sum).isGreaterThan(0);
    }

    @Test
    public void findOrderByUserAndCourse() {
        Long sum = orderRepository.countByCourseIdAndUserId(2L,1L);
        System.out.println(sum);
        assertThat(sum).isGreaterThan(0);
    }
}
