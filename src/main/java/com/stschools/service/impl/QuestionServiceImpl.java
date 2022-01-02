package com.stschools.service.impl;

import com.stschools.entity.Question;
import com.stschools.entity.Quiz;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.QuestionRepository;
import com.stschools.repository.QuizRepository;
import com.stschools.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    @Override
    public Long delete(Long id) {
        Question questionOld = questionRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));
        questionRepository.delete(questionOld);
        return id;
    }
}
