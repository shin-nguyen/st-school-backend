package com.stschools.common.laguage;

import com.stschools.entity.Language;
import com.stschools.entity.Topic;
import com.stschools.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)// keep data committed into the database  (Committed transaction for test -> LOG)
public class LanguageRepoTests {
    @Autowired
    LanguageRepository languageRepository;

    @Test
    public void testAddLanguage(){
        List<Language> listLanguage = Arrays.asList(
                new Language("English"),
                new Language("Vietnamese")
        );
        languageRepository.saveAll(listLanguage);

        Iterable<Language> iterable = languageRepository.findAll();
        assertThat(iterable).size().isEqualTo(2);
    }
}
